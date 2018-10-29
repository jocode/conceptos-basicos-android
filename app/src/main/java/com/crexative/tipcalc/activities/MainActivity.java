package com.crexative.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crexative.tipcalc.R;
import com.crexative.tipcalc.TipCalcApp;
import com.crexative.tipcalc.fragments.TipHistoryListFragment;
import com.crexative.tipcalc.fragments.TipHistoryListFragmentListener;
import com.crexative.tipcalc.models.TipRecord;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputBill) EditText inputBill;
    @BindView(R.id.inputPercentaje) EditText inputPercentaje;
    @BindView(R.id.txtTip) TextView txtTip;

    private final int DEFAULT_TIP_PERCENTAJE = 10;
    private final int TIP_STEP_CHANGE = 1;
    private TipHistoryListFragmentListener listFragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment listFragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        listFragment.setRetainInstance(true);
        listFragmentListener = (TipHistoryListFragmentListener) listFragment;
    }

    @OnClick(R.id.btnCalcular)
    public void handleClickSubmit(){
        Log.e(getLocalClassName(), "Click en botón calcular");
        hideKeyboard();

        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPercentaje = getTipPercentaje();
            double tip = total*(tipPercentaje/100d);

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentaje(tipPercentaje);
            tipRecord.setTimestamp(new Date());

            String strTrip = String.format(getString(R.string.global_message_tip), tip);
            listFragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTrip);
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        listFragmentListener.clearList();
    }

    private void handleTipChange(int change) {
        int tipPercentaje = getTipPercentaje();
        tipPercentaje += change;
        if (tipPercentaje>0){
            inputPercentaje.setText(String.valueOf(tipPercentaje));
        }
    }

    private int getTipPercentaje() {
        int tipPercentaje = DEFAULT_TIP_PERCENTAJE;
        String strTipPercentaje = inputPercentaje.getText().toString();
        if (!strTipPercentaje.isEmpty()){
            tipPercentaje = Integer.parseInt(strTipPercentaje);
        } else {
            inputPercentaje.setText(String.valueOf(tipPercentaje));
        }
        return tipPercentaje;
    }

    private void hideKeyboard() {
        InputMethodManager methodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        try {
            methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }  catch (NullPointerException e){
            Log.e(getLocalClassName(), Log.getStackTraceString(e));
        }
    }

    /**
     * Creamos las opciones de menú
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                about();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();

        // Intent implícito, donde abre el navegador
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
}
