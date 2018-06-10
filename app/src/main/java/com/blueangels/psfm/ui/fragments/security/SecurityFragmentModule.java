package com.blueangels.psfm.ui.fragments.security;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.blueangels.psfm.base.FragmentContext;
import com.blueangels.psfm.base.PerFragmentScope;

import dagger.Module;
import dagger.Provides;

import static com.blueangels.psfm.utils.Constants.CONSTANTS;

/**
 * Created by Leon on 25-12-17.
 */
@Module(includes = {FragmentContext.class})
@PerFragmentScope
public class SecurityFragmentModule {

    @Provides
    @PerFragmentScope
    SecurityFragmentPresenter providesSecurityFragmentPresenter(Context context) {
        return new SecurityFragmentPresenter(context);
    }

    @Provides
    @PerFragmentScope
    ArrayAdapter<String> providesStringArrayAdapter(Context context) {
        return new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, CONSTANTS);
    }
}
