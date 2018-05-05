package com.mpdam.info.tdsapp.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mpdam.info.tdsapp.Model.RapportResp;
import com.mpdam.info.tdsapp.R;
import com.mpdam.info.tdsapp.remote.APIService;
import com.mpdam.info.tdsapp.remote.ApiUtils;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RapportActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
  private EditText Edittitle,EditNote,EditId;
    private APIService mAPIService;
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private Uri uri;
    private static final String TAG = MainActivity.class.getSimpleName();
    String title,note,id1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport2);
        EditId=(EditText)findViewById(R.id.editid);
        Edittitle=(EditText)findViewById(R.id.edittitle);
        EditNote=(EditText)findViewById(R.id.editnote);
        mAPIService = ApiUtils.getAPIService();

    }
    public void Btn(View view) {
        id1=EditId.getText().toString();
      // int id=Integer.parseInt(id1);
        title=Edittitle.getText().toString();
        note=EditNote.getText().toString();
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, RapportActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                UpoldJSON(id1,title,note);



            }
            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            UpoldJSON(id1,title,note);
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }




    private void UpoldJSON(String id1, String title, String note) {
        String filePath = getRealPathFromURIPath(uri, RapportActivity.this);
        File file = new File(filePath);
        Log.d(TAG, "Filename " + file.getName());
        //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody title1 = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody note1 = RequestBody.create(MediaType.parse("text/plain"), note);
        RequestBody id11 = RequestBody.create(MediaType.parse("text/plain"), id1);

        mAPIService.create(title1,note1,id11,fileToUpload).enqueue(new Callback<RapportResp>() {
            @Override
            public void onResponse(Call<RapportResp> call, Response<RapportResp> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.body().getRapport().getId().toString() , Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),response.body().getRapport().getId().toString() , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<RapportResp> call, Throwable t) {
                Log.d(TAG, "Error " + t.getMessage());

            }
        });
    }


}
