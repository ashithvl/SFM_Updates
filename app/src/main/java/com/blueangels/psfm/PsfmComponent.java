package com.blueangels.psfm;

import dagger.Component;


@Component(modules = {PsfmAppModule.class,SharedPreferenceModule.class})
@PsfmScope
public interface PsfmComponent {

    void inject(PsfmApplication psfmApplication);

}
