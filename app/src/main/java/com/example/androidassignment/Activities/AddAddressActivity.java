package com.example.androidassignment.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidassignment.Helper.DBHandler;
import com.example.androidassignment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddAddressActivity extends AppCompatActivity {

    AppCompatEditText addressET, descriptionET;
    DBHandler dbHandler;
    Button add;
    ImageView addressImage;
    TextView upload;
    private static final int IMAGE_PICK_CODE = 1000;
    Uri imageUri;
    String ImageString = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initViews();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addressET.getText().toString().isEmpty()){
                    addressET.setError("Please Enter Address");
                }else if (descriptionET.getText().toString().isEmpty()){
                    descriptionET.setError("Please Enter Description");
                }else {
                    String address = addressET.getText().toString();
                    String description = descriptionET.getText().toString();

                    if (!ImageString.isEmpty()){
                        dbHandler.addImage(ImageString);
                        Log.e("Image Upload", "SuccessFull");
                        Log.e("ImageString", ImageString);
                    }

                    dbHandler.addNewAddress(address, description);

                    Toast.makeText(AddAddressActivity.this, "Address added successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddAddressActivity.this, MainActivity.class));
                    finish();

                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromGallery(IMAGE_PICK_CODE);
            }
        });
    }

    private void pickImageFromGallery(final int IMAGE_PIC_CODE) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PIC_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_CODE) {
                try {
                    imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    Picasso.get().load(imageUri).fit().centerInside().into(addressImage);
                    ImageString = getEncoded64ImageStringFromBitmap(selectedImage);
                    Log.e("imageUrl", ImageString);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(AddAddressActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(AddAddressActivity.this, "You haven't picked any image", Toast.LENGTH_SHORT).show();
        }
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();

        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }


    void initViews(){
        addressET = findViewById(R.id.addressET);
        descriptionET = findViewById(R.id.descriptionET);
        dbHandler = new DBHandler(AddAddressActivity.this);
        add = findViewById(R.id.add_address);
        addressImage = findViewById(R.id.address_photo);
        upload = findViewById(R.id.image_upload);
    }
}