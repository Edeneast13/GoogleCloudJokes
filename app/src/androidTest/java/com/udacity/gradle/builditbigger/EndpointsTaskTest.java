package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by brianroper on 5/16/16.
 */
public class EndpointsTaskTest extends AndroidTestCase {

    EndpointsTask mEndpointsTask;
    String mResult;
    Context mContext;

    public EndpointsTaskTest(Context context) {
        mContext = context;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mResult = null;

        mEndpointsTask = new EndpointsTask(){
            @Override
            protected void onPostExecute(String result) {

            }
        };
    }

    public void testReturn(){

        try{

            mEndpointsTask.execute(mContext);
            mResult = mEndpointsTask.get(10, TimeUnit.SECONDS);

            assertNotNull(mResult);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            fail(mContext.getResources().getString(R.string.test_time_out));
        }
        catch (ExecutionException e) {
            e.printStackTrace();
            fail(mContext.getResources().getString(R.string.test_time_out));
        }
        catch (TimeoutException e) {
            e.printStackTrace();
            fail(mContext.getResources().getString(R.string.test_time_out));
        }
    }
}
