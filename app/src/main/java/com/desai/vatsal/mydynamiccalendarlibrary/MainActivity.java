package com.desai.vatsal.mydynamiccalendarlibrary;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.AppConstants;
import com.desai.vatsal.mydynamiccalendar.EventModel;
import com.desai.vatsal.mydynamiccalendar.GetEventListListener;
import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;
import com.desai.vatsal.mydynamiccalendar.OnEventClickListener;
import com.desai.vatsal.mydynamiccalendar.OnWeekDayViewClickListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnCustomItemClickListener,OnCustomItemClickListenerSeconds{

    //  private Toolbar toolbar;
    private MyDynamicCalendar myCalendar;
    private TextView filter, filter1;
    int i=1;
    private ArrayList<ListData> list_data;
    private Dialog dialog;
    private int wfid2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCalendar = findViewById(R.id.myCalendar);
        //  toolbar =  findViewById(R.id.toolbar);
        filter = findViewById(R.id.filter);
        filter1 = findViewById(R.id.filter1);
        list_data=new ArrayList<>();
        //   setSupportActionBar(toolbar);
        filter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
               // AppConstants.eventList.clear();
                /*for (int i = 1; i < 10; i++) {
                    heloo(i, i, i, i);
                }*/
                if ((AppUtils.isNetworkAvailable(MainActivity.this))) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://demo.trakit.in/api/mobile/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Toast.makeText(MainActivity.this, "Hello1", Toast.LENGTH_SHORT).show();
                    NetworkService service = retrofit.create(NetworkService.class);
                    Log.i("GetShipment12345",""+retrofit.toString());
                    Call<LoginResponse> userInfoCall = service.getworkflows("02U4G2h3C-qosHKbhBCC5ZyQRsxggw==");
                    Log.i("GetShipment12345",""+userInfoCall.toString());
                    userInfoCall.enqueue(new Callback<LoginResponse>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                            Log.i("GetShipment1234567",""+response.isSuccessful());
                            if (response.isSuccessful()) {
                               // if (progressDialog != null) progressDialog.cancel();
                                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                                LoginResponse myItem = response.body();
                                assert myItem != null;
                                Log.i("GetShipment1234567",""+myItem.getInfo());
                                if(myItem.getCode() == 1) {
                                    list_data.clear();
                                    list_data.add(new ListData("All"));
                                    for (int i = 0; i < myItem.RetInfo.size(); i ++) {
                                        ListData itemsData = new ListData(myItem.RetInfo.get(i).WFID,myItem.RetInfo.get(i).WFName);
                                        list_data.add(itemsData);
                                    }
                                    dialog = new Dialog(MainActivity.this);
                                    dialog.setContentView(R.layout.activity_list);
                                    dialog.setTitle("Title...");
                                    RecyclerView myNames=  dialog.findViewById(R.id.recycler_view);
                                    // 2. set layoutManger
                                    myNames.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                    // 3. create an adapter
                                    FilterAdapter mAdapter = new FilterAdapter(list_data,MainActivity.this);
                                    myNames.setAdapter(mAdapter);
                                    myNames.setItemAnimator(new DefaultItemAnimator());
                                    dialog.show();
                                }
                                else {
                                  //  if (progressDialog != null) progressDialog.cancel();
                                    Toast.makeText(MainActivity.this, myItem.getInfo(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                          //  if (progressDialog != null) progressDialog.cancel();
                            Log.i("GetShipment123458",t.toString());
                            t.printStackTrace();
                        }
                    });
                } else {
                    AppUtils.showCustomAlert(Objects.requireNonNull(MainActivity.this), getResources().getString(R.string.message_network_problem));
                }


            }
        });


        filter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  AppConstants.eventList.clear();
               /* for (int i = 1; i < 12; i++) {
                    heloo(i, i, i, i);
                }*/
            filter2();
            }
        });

        heloo(i, i, i, i);

//        myCalendar.setCalendarDate(5, 10, 2016);

    }

    @SuppressLint("NewApi")
    private void filter2() {
        if ((AppUtils.isNetworkAvailable(MainActivity.this))) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://demo.trakit.in/api/mobile/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Toast.makeText(MainActivity.this, "Hello1", Toast.LENGTH_SHORT).show();
            NetworkService service = retrofit.create(NetworkService.class);
            Log.i("GetShipment12345",""+wfid2);
            Call<LoginResponseEvents> userInfoCall = service.getevents("02U4G2h3C-qosHKbhBCC5ZyQRsxggw==",wfid2);
            Log.i("GetShipment12345",""+userInfoCall.toString());
            userInfoCall.enqueue(new Callback<LoginResponseEvents>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<LoginResponseEvents> call, @NonNull Response<LoginResponseEvents> response) {
                    Log.i("GetShipment1234567",""+response.isSuccessful());
                    if (response.isSuccessful()) {
                        // if (progressDialog != null) progressDialog.cancel();
                        Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                        LoginResponseEvents myItem = response.body();
                        assert myItem != null;
                        Log.i("GetShipment1234567",""+myItem.getInfo());
                        if(myItem.getCode() == 1) {
                            list_data.clear();
                            list_data.add(new ListData("All"));
                            for (int i = 0; i < myItem.RetInfo.size(); i ++) {
                                ListData itemsData = new ListData(myItem.RetInfo.get(i).ID,myItem.RetInfo.get(i).Name);
                                list_data.add(itemsData);
                            }
                            dialog = new Dialog(MainActivity.this);
                            dialog.setContentView(R.layout.activity_list);
                            dialog.setTitle("Title...");
                            RecyclerView myNames=  dialog.findViewById(R.id.recycler_view);
                            // 2. set layoutManger
                            myNames.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            // 3. create an adapter
                            FilterAdapterSeconds mAdapter = new FilterAdapterSeconds(list_data,MainActivity.this);
                            myNames.setAdapter(mAdapter);
                            myNames.setItemAnimator(new DefaultItemAnimator());
                            dialog.show();
                        }
                        else {
                            //  if (progressDialog != null) progressDialog.cancel();
                            Toast.makeText(MainActivity.this, myItem.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<LoginResponseEvents> call, @NonNull Throwable t) {
                    //  if (progressDialog != null) progressDialog.cancel();
                    Log.i("GetShipment123458",t.toString());
                    t.printStackTrace();
                }
            });
        } else {
            AppUtils.showCustomAlert(Objects.requireNonNull(MainActivity.this), getResources().getString(R.string.message_network_problem));
        }
    }

    private void heloo(final int i, final int i1, final int i2, final int i3) {
        myCalendar.showMonthView();


        myCalendar.setCalendarBackgroundColor("#ffffff");
        // myCalendar.setCalendarBackgroundColor("#eeeeee");
//        myCalendar.setCalendarBackgroundColor(R.color.gray);

        myCalendar.setHeaderBackgroundColor("#454265");
//        myCalendar.setHeaderBackgroundColor(R.color.black);

        myCalendar.setHeaderTextColor("#ffffff");
//        myCalendar.setHeaderTextColor(R.color.white);

        myCalendar.setNextPreviousIndicatorColor("#245675");
//        myCalendar.setNextPreviousIndicatorColor(R.color.black);

        myCalendar.setWeekDayLayoutBackgroundColor("#965471");
//        myCalendar.setWeekDayLayoutBackgroundColor(R.color.black);

        // myCalendar.setWeekDayLayoutTextColor("#ffffff");
        //  myCalendar.setWeekDayLayoutTextColor("#ff0000");
        myCalendar.setWeekDayLayoutTextColor("#ffffff");
//        myCalendar.setWeekDayLayoutTextColor(R.color.black);

      /*  myCalendar.isSaturdayOff(true, "#ffffff", "#000000");
        myCalendar.isSaturdayOff(true, R.color.white, R.color.black);

        myCalendar.isSundayOff(true, "#658745", "#254632");
        myCalendar.isSundayOff(true, R.color.white, R.color.red);*/
        myCalendar.setExtraDatesOfMonthBackgroundColor("#ffffff");
        //   myCalendar.setExtraDatesOfMonthBackgroundColor("#324568");
//        myCalendar.setExtraDatesOfMonthBackgroundColor(R.color.black);


//        myCalendar.setExtraDatesOfMonthTextColor(R.color.black);

//        myCalendar.setDatesOfMonthBackgroundColor(R.drawable.event_view);
        myCalendar.setDatesOfMonthBackgroundColor("#ffffff");
//        myCalendar.setDatesOfMonthBackgroundColor(R.color.black);

        myCalendar.setDatesOfMonthTextColor("#000000");
        //   myCalendar.isSundayOff(true, "#ffffff", "#ff0000");
        myCalendar.setDatesOfMonthTextColor(R.color.black);
        myCalendar.setExtraDatesOfMonthTextColor("#787878");
//        myCalendar.setCurrentDateBackgroundColor("#123412");
        myCalendar.setCurrentDateBackgroundColor("#ff0000");

        myCalendar.setCurrentDateTextColor("#000000");
//        myCalendar.setCurrentDateTextColor(R.color.black);

        myCalendar.setEventCellBackgroundColor("#852365");
//        myCalendar.setEventCellBackgroundColor(R.color.black);

        myCalendar.setEventCellTextColor("#425684");
//        myCalendar.setEventCellTextColor(R.color.black);

        Log.i("Log_data", "" + i);
        myCalendar.addEvent(i + "-" + i + "-" + "2018", "8:15", "8:30", "" + i);
        /*        myCalendar.addEvent("30-07-2018", "8:15", "8:30", "Today Event 2");*/
       /* myCalendar.addEvent("05-07-2018", "8:30", "8:45", "Today Event 3");
        myCalendar.addEvent("05-10-2016", "8:45", "9:00", "Today Event 4");
        myCalendar.addEvent("8-10-2016", "8:00", "8:30", "Today Event 5");
        myCalendar.addEvent("08-10-2016", "9:00", "10:00", "Today Event 6");*/

        myCalendar.getEventList(new GetEventListListener() {
            @Override
            public void eventList(ArrayList<EventModel> eventList) {

                Log.e("tag", "eventList.size():-" + eventList.size());
                for (int i = 0; i < eventList.size(); i++) {
                    Log.e("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                }

            }
        });
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));

            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

//        myCalendar.updateEvent(0, "5-10-2016", "8:00", "8:15", "Today Event 111111");

//        myCalendar.deleteEvent(2);

//        myCalendar.deleteAllEvent();

        myCalendar.setBelowMonthEventTextColor("#425684");
//        myCalendar.setBelowMonthEventTextColor(R.color.black);

        myCalendar.setBelowMonthEventDividerColor("#635478");
//        myCalendar.setBelowMonthEventDividerColor(R.color.black);

        myCalendar.setHolidayCellBackgroundColor("#654248");
//        myCalendar.setHolidayCellBackgroundColor(R.color.black);

        myCalendar.setHolidayCellTextColor("#d590bb");
//        myCalendar.setHolidayCellTextColor(R.color.black);

        myCalendar.setHolidayCellClickable(false);
        myCalendar.addHoliday("2-11-2016");
        myCalendar.addHoliday("8-11-2016");
        myCalendar.addHoliday("12-11-2016");
        myCalendar.addHoliday("13-11-2016");
        myCalendar.addHoliday("8-10-2016");
        myCalendar.addHoliday("10-12-2016");
        myCalendar.showMonthViewWithBelowEvents();

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_month:
                showMonthView();
                return true;
            case R.id.action_month_with_below_events:
                showMonthViewWithBelowEvents();
                return true;
            case R.id.action_week:
                showWeekView();
                return true;
            case R.id.action_day:
                showDayView();
                return true;
            case R.id.action_agenda:
                showAgendaView();
                return true;
            case R.id.action_today:
                myCalendar.goToCurrentDate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }*/

    private void showMonthView() {

        myCalendar.showMonthView();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }

    private void showMonthViewWithBelowEvents() {

        myCalendar.showMonthViewWithBelowEvents();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }

    private void showWeekView() {

        myCalendar.showWeekView();

        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showWeekView", "from setOnEventClickListener onClick");
            }

            @Override
            public void onLongClick() {
                Log.e("showWeekView", "from setOnEventClickListener onLongClick");

            }
        });

        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {
                Log.e("showWeekView", "from setOnWeekDayViewClickListener onClick");
                Log.e("tag", "date:-" + date + " time:-" + time);

            }

            @Override
            public void onLongClick(String date, String time) {
                Log.e("showWeekView", "from setOnWeekDayViewClickListener onLongClick");
                Log.e("tag", "date:-" + date + " time:-" + time);

            }
        });


    }

    private void showDayView() {

        myCalendar.showDayView();

        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showDayView", "from setOnEventClickListener onClick");

            }

            @Override
            public void onLongClick() {
                Log.e("showDayView", "from setOnEventClickListener onLongClick");

            }
        });

        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {
                Log.e("showDayView", "from setOnWeekDayViewClickListener onClick");
                Log.e("tag", "date:-" + date + " time:-" + time);
            }

            @Override
            public void onLongClick(String date, String time) {
                Log.e("showDayView", "from setOnWeekDayViewClickListener onLongClick");
                Log.e("tag", "date:-" + date + " time:-" + time);
            }
        });

    }

    private void showAgendaView() {

        myCalendar.showAgendaView();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }


    @Override
    public void onItemClickListener(int position, String item,int wfid1) {
        filter.setText(item);
        dialog.dismiss();
        wfid2=wfid1;
    }

    @Override
    public void onItemClickListenerSeconds(int position, String item, int wfid1) {
        filter1.setText(item);
        dialog.dismiss();

    }
}
