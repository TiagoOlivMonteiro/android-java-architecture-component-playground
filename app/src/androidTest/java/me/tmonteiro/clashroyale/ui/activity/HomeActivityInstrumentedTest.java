package me.tmonteiro.clashroyale.ui.activity;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.intercepting.SingleActivityFactory;
import android.support.v4.app.FragmentActivity;
import android.test.mock.MockApplication;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import dagger.android.DaggerApplication;
import me.tmonteiro.clashroyale.di.DaggerAppComponent;
import me.tmonteiro.clashroyale.ui.activity.HomeActivity;
import me.tmonteiro.clashroyale.viewmodel.ViewModelProviderWrapper;
import me.tmonteiro.clashroyale.viewmodel.card.CardViewModel;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@RunWith(AndroidJUnit4.class)
public class HomeActivityInstrumentedTest {

    @Mock
    ViewModelProvider.Factory mockViewModelFactory;

    @Mock
    ViewModelProviderWrapper mockProviderWrapper;

    @Mock
    CardViewModel mockCardViewModel;

    @Rule
    public TestRule testRule;

    private Robots robots;

    private SingleActivityFactory<HomeActivity> activitySingleActivityFactory =
            new SingleActivityFactory<HomeActivity>(HomeActivity.class) {
        @Override
        protected HomeActivity create(Intent intent) {
            HomeActivity homeActivity = new HomeActivity();
            homeActivity.setFactory(mockViewModelFactory);
            homeActivity.setProviderWrapper(mockProviderWrapper);
            return homeActivity;
        }
    };

    @Rule
    public ActivityTestRule<HomeActivity> rule
            = new ActivityTestRule(activitySingleActivityFactory, true, false);


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        testRule = new InstantTaskExecutorRule();
        robots = new Robots();
    }

    @Test
    public void testing(){

        // Arrange
        Intents.init();
        robots.mockCardViewModel();


        // Act
        rule.launchActivity(new Intent());

        // Assert
        Intents.release();

        Log.d("","");
    }

    public class Robots {

        public Robots mockCardViewModel(){

            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return mockCardViewModel;
                }
            }).when(mockProviderWrapper).of(any(FragmentActivity.class), any(ViewModelProvider.Factory.class));

            return this;
        }
    }

}
