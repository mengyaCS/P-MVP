package me.passin.pmvp.example.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;

import me.passin.pmvp.example.mvp.ui.activity.UserActivity;
import me.passin.pmvp.globalconfig.constant.ComponentActionConstant;
import me.passin.pmvp.globalconfig.constant.ComponentNameConstant;


/**
 * @author billy.qi
 */
public class ExampleComponent implements IComponent {

//    public static final String COMPONENT_NAME_A = "module_fragmentation";
    @Override
    public String getName() {
        //组件的名称，调用此组件的方式：
        // CC.obtainBuilder("ComponentA")...build().callAsync()
        return ComponentNameConstant.MODULE_EXAMPLE;
    }

    /**
     * 组件被调用时的入口
     * 要确保每个逻辑分支都会调用到CC.sendCCResult，
     * 包括try-catch,if-else,switch-case-default,startActivity
     * @param cc 组件调用对象，可从此对象中获取相关信息
     * @return true:将异步调用CC.sendCCResult(...),用于异步实现相关功能，例如：文件加载、网络请求等
     *          false:会同步调用CC.sendCCResult(...),即在onCall方法return之前调用，否则将被视为不合法的实现
     */
    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        switch (actionName) {
            case ComponentActionConstant.SHOW_HOME_ACTIVITY:
                openActivity(cc);
                break;
            default:
                //这个逻辑分支上没有调用CC.sendCCResult(...),是一种错误的示例
                //并且方法的返回值为false，代表不会异步调用CC.sendCCResult(...)
                //在LocalCCInterceptor中将会返回错误码为-10的CCResult
                break;
        }
        return false;
    }

    private void openActivity(CC cc) {
        Context context = cc.getContext();
        Intent intent = new Intent(context, UserActivity.class);
        if (!(context instanceof Activity)) {
            //调用方没有设置context或app间组件跳转，context为application
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        CC.sendCCResult(cc.getCallId(), CCResult.success("cc","ok"));
    }
}
