package com.appolica.sample.ui.editor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.appolica.sample.ui.editor.pager.EditorFragmentType;
import com.appolica.sample.ui.editor.pager.ListenerProvider;
import com.appolica.sample.ui.editor.pager.animations.AnimationsFragment;
import com.appolica.sample.ui.editor.pager.interpolators.InterpolatorsFragment;

public class EditViewPagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    private ListenerProvider listenerProvider;

    public EditViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        final EditorFragmentType fragmentType = EditorFragmentType.values()[position];

        return context.getString(fragmentType.getNameId());
    }

    @Override
    public Fragment getItem(int position) {
        final EditorFragmentType fragmentType = EditorFragmentType.values()[position];

        return fragmentType.getInstance();
    }

    @Override
    public int getCount() {
        return EditorFragmentType.values().length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Fragment item = (Fragment) super.instantiateItem(container, position);

        switch (EditorFragmentType.values()[position]) {
            case ANIMATIONS:
                ((AnimationsFragment) item).setSelectedListener(listenerProvider.getAnimationSelectedListener());
                break;
            case INTERPOLATORS:
                ((InterpolatorsFragment) item).setSelectedListener(listenerProvider.getInterpolatorSelectedListener());
                break;
            case SETTINGS:

                break;
        }

        return item;
    }

    public void setListenerProvider(ListenerProvider listenerProvider) {
        this.listenerProvider = listenerProvider;
    }
}
