package sw.es.domain.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.animation.OvershootInterpolator;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by albertopenasamor on 16/11/15.
 */
public class RecyclerSlideInUpAnimator implements ItemAnimatorBuilder{

    @Override
    public RecyclerView.ItemAnimator build() {
        RecyclerView.ItemAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        int animMS = 1000;
        animator.setAddDuration(animMS);
        animator.setRemoveDuration(animMS);
        animator.setMoveDuration(animMS);
        animator.setChangeDuration(animMS);
        return animator;
    }
}
