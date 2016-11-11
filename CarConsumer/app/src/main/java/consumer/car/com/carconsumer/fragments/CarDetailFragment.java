package consumer.car.com.carconsumer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import consumer.car.com.carconsumer.R;
import consumer.car.com.carconsumer.model.Car;

/**
 * Created by root on 05/11/16.
 */

public class CarDetailFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    public static final String POSITION = "position";
    public static final String CAR = "car";

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private TextView idScrollViewTextViewCarDescription;
    private TextView idToolBarCarName;
    private TextView detailTextViewCarName;
    private TextView detailTextViewCarType;
    private ImageView idImageViewAppBarCar;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private Car car;
    private int position;
    private int idCar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_car_detail, container, false);

        Intent intent = getActivity().getIntent();

        car = (Car) intent.getSerializableExtra(CAR);

        // The ID reference of the component_tool_bar_layout_cars file
        mToolbar        = (Toolbar) v.findViewById(R.id.id_main_widget_toolbar_cars);
        // The ID reference TextView from component_tool_bar_layout_cars file
        mTitle          = (TextView) v.findViewById(R.id.id_tool_bar_car_name);
        // The ID reference LinearLayout inside CollapsingToolbarLayout from component_app_bar_layout_cars file
        mTitleContainer = (LinearLayout) v.findViewById(R.id.id_linear_layout_title_colapsing_tool_bar_layout_car_name);
        // The ID reference id_app_bar_layout_cars from component_app_bar_layout_cars file
        mAppBarLayout   = (AppBarLayout) v.findViewById(R.id.id_app_bar_layout_cars);

        // The ID reference TextView from component_card_view_cars_detail inside component_nested_scroll_view_layuout_cars file
        // (This is the Car description text and will be shown on the main screen of detail fragment)
        idScrollViewTextViewCarDescription = (TextView) v.findViewById(R.id.id_scroll_view_text_view_car_description);

        // The ID reference TextView inside CollapsingToolbarLayout from component_app_bar_layout_cars file
        // (This is the Car name text and will be shown on the app bar detail when switched down by user)
        detailTextViewCarName = (TextView) v.findViewById(R.id.detail_text_view_car_name);

        // The ID reference TextView inside CollapsingToolbarLayout from component_app_bar_layout_cars file
        // (This is the Car type text and will be shown on the app bar detail when switched down by user)
        detailTextViewCarType = (TextView) v.findViewById(R.id.detail_text_view_car_type);

        // The ID reference ImageView inside CollapsingToolbarLayout from component_app_bar_layout_cars file
        // (This image will appear when switch tool bar down on collapsing toolbar layout)
        idImageViewAppBarCar = (ImageView) v.findViewById(R.id.id_image_view_app_bar_car);

        // The ID reference TextView from component_tool_bar_layout_cars file
        // (It will be the name of the Car on tool bar)
        idToolBarCarName = (TextView) v.findViewById(R.id.id_tool_bar_car_name);


        mAppBarLayout.addOnOffsetChangedListener(this);
        mToolbar.inflateMenu(R.menu.menu_main_cars);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        addLayoutFieldsData(car);

        return v;
    }

    private void addLayoutFieldsData(Car car) {

        idCar = car.getId();

        detailTextViewCarType.setText(car.getType());
        idScrollViewTextViewCarDescription.setText(car.getDescription());
        detailTextViewCarName.setText(car.getName());
        mTitle.setText(car.getName());
        idImageViewAppBarCar.setImageBitmap(car.getCarImageByte());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main_cars, menu);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);

    }


    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
