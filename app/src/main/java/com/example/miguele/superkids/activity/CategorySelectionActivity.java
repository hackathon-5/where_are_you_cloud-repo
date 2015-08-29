package com.example.miguele.superkids.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguele.superkids.R;
import com.example.miguele.superkids.adapter.CategoryAdapter;
import com.example.miguele.superkids.model.Category;
import com.example.miguele.superkids.storage.SuperDB;

import java.util.ArrayList;
import java.util.List;

public class CategorySelectionActivity extends Activity {
    private static final String TAG = "activity_category_selection";

    GridView gridView;
    TextView totalTimeTxt;
    CategoryAdapter categoryAdapter;
    List<Category> mCategories;
    Category mSelectedCategory;
    Context mContext;

    Category mNewCategory;

    private int totalUsage;
    private int increment;
    private int accumulator;
    private boolean changedIncrement = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
        mCategories = new ArrayList<>();
        mContext = this;
        initData();
        setupUI();
    }

    private void initData() {


        // School
        Category school = new Category();
        school.setId(R.drawable.ic_school);
        school.setName("School");
        school.setIncrements(1);
        school.setIntervals(2);
        mCategories.add(school);
        // Bedtime
        Category bedTime = new Category();
        bedTime.setId(R.drawable.ic_brightness);
        bedTime.setName("BedTime");
        bedTime.setIncrements(1);
        bedTime.setIntervals(2);
        mCategories.add(bedTime);
        // sports
        Category sport = new Category();
        sport.setId(R.drawable.bike);
        sport.setName("Sports");
        sport.setIncrements(1);
        sport.setIntervals(2);
        mCategories.add(sport);
        // hygiene
        Category hygiene = new Category();
        hygiene.setId(R.drawable.ic_favorite);
        hygiene.setName("Hygiene");
        hygiene.setIncrements(1);
        hygiene.setIntervals(2);
        mCategories.add(hygiene);
        // achievement
        Category achieve = new Category();
        achieve.setId(R.drawable.ic_star_white_24dp);
        achieve.setName("Achievement");
        achieve.setIncrements(1);
        achieve.setIntervals(2);
        mCategories.add(achieve);
        // add new
        Category newCat = new Category();
        newCat.setId(R.drawable.ic_add);
        newCat.setName("new");
        newCat.setIncrements(1);
        newCat.setIntervals(2);
        mCategories.add(newCat);


        totalUsage = 12;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupUI() {
        totalTimeTxt = (TextView) findViewById(R.id.total_time_text);
        setupGridView();
    }

    private void setupGridView() {
        gridView = (GridView) findViewById(R.id.category_grid_view);

        categoryAdapter = new CategoryAdapter(this, mCategories);
        gridView.setAdapter(categoryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                mSelectedCategory = mCategories.get(position);
//
                // Create dialog for category
                final Dialog categoryDialog = new Dialog(mContext);
                categoryDialog.setContentView(R.layout.dialog_category);

                accumulator = 0;
                // Set dialog components
                ImageView dialogIcon = (ImageView) categoryDialog
                        .findViewById(R.id.dialog_icon);
                EditText categoryEdit = (EditText) categoryDialog
                        .findViewById(R.id.category_name_edit_txt);
                final TextView intervalEdit = (TextView) categoryDialog
                        .findViewById(R.id.interval_edit_txt);
                Button negBtn = (Button) categoryDialog
                        .findViewById(R.id.dec_btn);
                Button posBtn = (Button) categoryDialog
                        .findViewById(R.id.inc_bttn);
                Button doneBtn = (Button) categoryDialog
                        .findViewById(R.id.next_btn);

                if (mSelectedCategory.getName() != "new") {
                 // Set up proper information
                    dialogIcon.setImageResource(mSelectedCategory.getId());
                    categoryEdit.setText(mSelectedCategory.getName());
                }

                increment = mSelectedCategory.getIncrements();

                negBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        increment--;
                        if (increment == -1) {
                            increment = 0;
                        }
                        Log.i(TAG, Integer.toString(increment));
                        changedIncrement = true;
                        String totalTime = Integer.toString(increment * 2) + " mins.";
                        accumulator = increment * 2;
//                        Log.i(TAG, "tU" + Integer.toString(totalUsage));
                        intervalEdit.setText(totalTime);
                    }
                });

                posBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        increment++;
                        Log.i(TAG, Integer.toString(increment));
                        changedIncrement = true;
                        String totalTime = Integer.toString(increment * 2) + " mins.";
                        accumulator = increment * 2;
//                        Log.i(TAG, "tU" + Integer.toString(totalUsage));
                        intervalEdit.setText(totalTime);
                    }
                });

                doneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        categoryDialog.cancel();
                    }
                });

//                Toast.makeText(mContext, mSelectedCategory.getName(), Toast.LENGTH_SHORT).show();
                categoryDialog.setCanceledOnTouchOutside(true);
                categoryDialog.show();
                categoryDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        // Notify of all changes
                        totalUsage += accumulator;
                        if (changedIncrement) {
                            totalUsage = totalUsage -2;
                        }
                        Toast.makeText(mContext, "Closing dialog", Toast.LENGTH_SHORT).show();
                        categoryAdapter.notifyDataSetChanged();
                        gridView.invalidateViews();
                        totalTimeTxt.setText(Integer.toString(totalUsage) + " mins.");
                        SuperDB.setTimeUsage(mContext, totalUsage);

                    }
                });

            }
        });

    }
}
