package com.example.miguele.superkids.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.miguele.superkids.R;
import com.example.miguele.superkids.model.Category;

import java.util.List;

/**
 * Created by miguele on 8/29/15.
 */
public class CategoryAdapter extends BaseAdapter {
    private static final String TAG = "adapter_category";
    private Context mContext;
    private List<Category> mCategories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.mContext = context;
        this.mCategories = categories;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.i(TAG,"Creating view!");
        View gridView;

        final Category mSelectedCategory = mCategories.get(position);

        if (convertView == null) {
            gridView = new View(mContext);

            // get layout from item_category.xml
            gridView = inflater.inflate(R.layout.item_category, null);

            ImageView fab = (ImageView) gridView
                    .findViewById(R.id.category_item_iv);

            int resourceId = mCategories.get(position).getId();
            fab.setImageResource(resourceId);

//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Create dialog for category
//                    final Dialog categoryDialog = new Dialog(mContext);
//                    categoryDialog.setContentView(R.layout.dialog_category);
//
//                    // Set dialog components
//                    ImageView dialogIcon = (ImageView) categoryDialog
//                            .findViewById(R.id.dialog_icon);
//                    EditText categoryEdit = (EditText) categoryDialog
//                            .findViewById(R.id.category_name_edit_txt);
//                    EditText intervalEdit = (EditText) categoryDialog
//                            .findViewById(R.id.interval_edit_txt);
//                    Button negBtn = (Button) categoryDialog
//                            .findViewById(R.id.dec_btn);
//                    Button posBtn = (Button) categoryDialog
//                            .findViewById(R.id.inc_bttn);
//
//                    if (mSelectedCategory.getName() != "new") {
//                        // Set up proper information
//                    }
//
//                    Toast.makeText(mContext, mSelectedCategory.getName(), Toast.LENGTH_SHORT).show();
//                    categoryDialog.setCanceledOnTouchOutside(true);
//                    categoryDialog.show();
//                }
//            });
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCategories.get(position).getId();
    }
}
