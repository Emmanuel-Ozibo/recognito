package com.example.user.recognito.Settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import com.example.user.recognito.R;


/**
 * Created by emmanuel on 2018-04-26.
 */


public class SettingsFragment extends PreferenceFragmentCompat{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_pref);
    }
}
