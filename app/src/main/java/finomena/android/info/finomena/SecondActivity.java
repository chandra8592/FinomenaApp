package finomena.android.info.finomena;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    String[] colorlist = {"#00FF00", "#00FF33", "#00FF66", "#00FF99", "#00FFCC", "#00FFFF",
            "#330000", "#330033", "#330066", "#330099", "#3300CC", "#3300FF", "#333300",
            "#333333", "#333366", "#333399", "#3333CC", "#3333FF", "#336600", "#336633",
            "#336666", "#336699", "#3366CC", "#3366FF", "#339900", "#339933", "#339966",
            "#339999", "#3399CC", "#3399FF", "#33CC00", "#33CC33", "#33CC66", "#33CC99",
            "#33CCCC", "#33CCFF", "#33FF00", "#33FF33", "#33FF66", "#33FF99", "#33FFCC",
            "#33FFFF", "#660000", "#660033", "#660066", "#660099", "#6600CC", "#6600FF",
            "#663300", "#663333", "#663366", "#663399", "#6633CC", "#6633FF", "#666600",
            "#666633", "#666666", "#666699", "#6666CC", "#6666FF", "#669900", "#669933",
            "#669966", "#669999", "#6699CC", "#6699FF", "#66CC00", "#66CC33", "#66CC66",
            "#66CC99", "#66CCCC", "#66CCFF", "#66FF00", "#66FF33", "#66FF66", "#66FF99",
            "#66FFCC", "#66FFFF", "#990000", "#990033", "#990066", "#990099", "#9900CC",
            "#9900FF", "#993300", "#993333", "#993366", "#993399", "#9933CC", "#9933FF",
            "#996600", "#996633", "#996666", "#996699", "#9966CC", "#9966FF", "#999900",
            "#999933", "#999966", "#999999", "#9999CC", "#9999FF", "#99CC00", "#99CC33",
            "#99CC66", "#99CC99", "#99CCCC", "#99CCFF", "#99FF00", "#99FF33", "#99FF66",
            "#99FF99", "#99FFCC", "#99FFFF", "#CC0000", "#CC0033", "#CC0066", "#CC0099",
            "#CC00CC", "#CC00FF", "#CC3300", "#CC3333", "#CC3366", "#CC3399", "#CC33CC",
            "#CC33FF", "#CC6600", "#CC6633", "#CC6666", "#CC6699", "#CC66CC", "#CC66FF",
            "#CC9900", "#CC9933", "#CC9966", "#CC9999", "#CC99CC", "#CC99FF", "#CCCC00",
            "#CCCC33", "#CCCC66", "#CCCC99", "#CCCCCC", "#CCCCFF", "#CCFF00", "#CCFF33",
            "#CCFF66", "#CCFF99", "#CCFFCC", "#CCFFFF", "#FF0000", "#FF0033", "#FF0066",
            "#FF0099", "#FF00CC", "#FF00FF", "#FF3300", "#FF3333", "#FF3366", "#FF3399",
            "#FF33CC", "#FF33FF", "#FF6600", "#FF6633", "#FF6666", "#FF6699", "#FF66CC",
            "#FF66FF", "#FF9900", "#FF9933", "#FF9966", "#FF9999", "#FF99CC", "#FF99FF",
            "#FFCC00", "#FFCC33", "#FFCC66", "#FFCC99", "#FFCCCC", "#FFCCFF", "#FFFF00",
            "#FFFF33", "#FFFF66", "#FFFF99", "#FFFFCC"};
    CustomGrid adapter;

    public static GridView gridview;
    int length;
    private int touch=0;
    private final int UNSELECTED = 500;
    private final int SELECTED = 501;
    private final int DISABLED = 502;

    int[] indexList;
    int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();
        String tiles_value = bundle.getString("value");
        length = Integer.parseInt(tiles_value);
        adapter = new CustomGrid(SecondActivity.this);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setNumColumns(length);
        gridview.setAdapter(adapter);
        indexList = new int[count];
        for (int i = 0; i < count; i++) {
            indexList[i] = UNSELECTED;
        }
        Log.i("count0",String.valueOf(touch));

    }

    @Override
    public void onClick(View view) {
        touch++;

    }


    public class CustomGrid extends BaseAdapter {
        private Context mContext;
        private Random rand = new Random();
        int rand_ind = rand.nextInt((length * length) - 0) + 0;

        public CustomGrid(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            count = length * length;

            return count;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View grid = new View(mContext);
            grid = inflater.inflate(R.layout.square, null);
            final View imageView = (ImageView) grid.findViewById(R.id.image_grid);

            if (convertView == null) {
                if (position == rand_ind) {
                    for (int i = 0; i < count; i++) {
                        imageView.setBackgroundResource(R.drawable.border_layout);
                        indexList[position] = SELECTED;

                    }
                } else {
                    String randomStr = colorlist[new Random().nextInt(colorlist.length)];
                    imageView.setBackgroundColor(Color.parseColor(randomStr));
                }
            } else {
                grid = (View) convertView;
            }

        grid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                    // get pointer index from the event object
                    int pointerIndex = motionEvent.getActionIndex();

                    // get pointer ID
                    int pointerId = motionEvent.getPointerId(pointerIndex);

                    // get masked (not specific to a pointer) action
                    int maskedAction = motionEvent.getActionMasked();

                    switch (maskedAction) {

                        case MotionEvent.ACTION_DOWN:{
                            Log.d("TouchTest", "Touch down");
                            Log.i("android....", "status " + indexList[position] + " size " + indexList.length);
                            Log.i("count1",String.valueOf(touch));
                          if(touch<count-1) {
                              Log.i("count",String.valueOf(touch));
                              if (indexList[position] == SELECTED) {

                                  int cur = rand.nextInt(count - 0);

                                  for (int i = 0; i < indexList.length; i++) {
                                      if (indexList[cur] == SELECTED || indexList[cur] == DISABLED) {
                                          cur = rand.nextInt(count - 0);

                                      } else {
                                          i = indexList.length;
                                      }
                                  }
                                  indexList[cur] = SELECTED;
                                  indexList[position] = DISABLED;

                                  Log.i("android....", "random no" + cur + " size " + indexList.length);

                                  gridview.getChildAt(cur).findViewById(R.id.image_grid).setBackgroundResource(R.drawable.border_layout);
                                  touch++;
                              }
                              else if (indexList[position] == DISABLED || indexList[position] == UNSELECTED) {

                                  Toast.makeText(SecondActivity.this, "Game Over...!", Toast.LENGTH_SHORT).show();
                                  finish();

                              }
                          }
                            else{

                              Toast.makeText(SecondActivity.this, "Game Over...!", Toast.LENGTH_SHORT).show();
                              String data="Maximum no of touch allowed by device is "+count+" \r\n No of fingers per person he can hold on to device is "+((int)(Math.ceil((float)count/2)));
                              showdialog(data);
                          }
                            break;
                        }

                        case MotionEvent.ACTION_UP:{

                            Toast.makeText(SecondActivity.this, "Game Over...!", Toast.LENGTH_SHORT).show();
                            finish();
//
                            break;
                        }
                    }


                    return true;
                }

        });
        return grid;
                }

    }
//    private void showdialog() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                this);
//
//        alertDialogBuilder
//                .setTitle("Game Over")
//                .setMessage("Do you want to Restart the game?")
//                .setCancelable(false)
//                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        finish();
//
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Intent startMain = new Intent(Intent.ACTION_MAIN);
//                        startMain.addCategory(Intent.CATEGORY_HOME);
//                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(startMain);
//                        finish();
//                    }
//                });
//
//        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // show it
//        alertDialog.show();
//
//
//    }
//    private void limit(){
//
//        int FinishTime = 10;
//        int countDownInterval = 1000;
//        CountDownTimer counterTimer = new CountDownTimer(FinishTime * 1000, countDownInterval) {
//            public void onFinish() {
//                //finish your activity here
//                showdialog();
//            }
//
//            public void onTick(long millisUntilFinished) {
////                Toast.makeText(getApplicationContext(),"Your Turn",Toast.LENGTH_SHORT).show();
//                final Toast toast = Toast.makeText(getApplicationContext(),  String.valueOf(millisUntilFinished/1000)+" seconde to go", Toast.LENGTH_SHORT);
//                toast.show();
//
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        toast.cancel();
//                    }
//                }, 1000);
//            }
//        };
//        counterTimer.start();
//    }
    private void showdialog(String data) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder
                .setTitle("Rules")
                .setMessage(data)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();

                    }
                });
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }
    @Override
    public void onBackPressed() {

    }
}



