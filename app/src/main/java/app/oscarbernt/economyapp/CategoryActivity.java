package app.oscarbernt.economyapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class CategoryActivity extends ActionBarActivity {
    DatabaseHelper db;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        db = new DatabaseHelper(getApplicationContext());
        lv = (ListView) findViewById(R.id.lvCat);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
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

    private void populateListView(){
        Cursor cursor = db.readCategories();
        String[] from = new String[] { DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_CURRVAL };
        int[] to = new int[] { R.id.column_name, R.id.column_id };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                CategoryActivity.this, R.layout.view_category_entry, cursor, from, to);

        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

/*        // OnCLickListiner For List Items
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                memID_tv = (TextView) view.findViewById(R.id.member_id);
                memName_tv = (TextView) view.findViewById(R.id.member_name);

                String memberID_val = memID_tv.getText().toString();
                String memberName_val = memName_tv.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),
                        Modify_member.class);
                modify_intent.putExtra("memberName", memberName_val);
                modify_intent.putExtra("memberID", memberID_val);
                startActivity(modify_intent);
            }
        });*/
    }


}
