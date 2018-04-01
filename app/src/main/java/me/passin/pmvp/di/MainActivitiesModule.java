package me.passin.pmvp.di;

import com.passin.pmvp.di.component.BaseActivityComponent;
import com.passin.pmvp.di.scope.PageScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.passin.pmvp.di.module.MainActivityModule;
import me.passin.pmvp.mvp.MainActivity;

/**
 * <pre>
 * @author : passin
 * Contact me : https://github.com/passin95
 * Date: 2018/3/21 14:41
 * </pre>
 */
@Module(subcomponents = {BaseActivityComponent.class})
public abstract class MainActivitiesModule {


    @PageScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivity();

}
