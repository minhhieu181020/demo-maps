package com.example.demo;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView imgSearch;
    private Button btnAdd, btnEdit, btnDelete;
    private EditText edlongtitude, edlatitude;
    private AutoCompleteTextView actTitle;
    private MapsDAO mapsDAO;
//    private List<Maps> mapsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapsDAO = new MapsDAO(this);
        actTitle = findViewById(R.id.actTitle);
        imgSearch = findViewById(R.id.imgSearch);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        edlongtitude = findViewById(R.id.edtLongitude);
        edlatitude = findViewById(R.id.edtLatitude);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        address();
        final List<Maps> mapsList = mapsDAO.selectMAPS();
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Kiểm tra quyền hạn
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        }
        // Add a marker in Sydney and move the camera
        LatLng mapsLng = new LatLng((mapsList.get(2).getLatitude()), (mapsList.get(2).getLongtitude()));
        mMap.addMarker(new MarkerOptions().position(mapsLng).title(mapsList.get(2).getTitle() + ""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mapsLng));
        mMap.setMyLocationEnabled(true);
        try {
            String autoMaps[] = new String[mapsList.size()];
            for (int i = 0; i < mapsList.size(); i++) {
                autoMaps[i] = mapsList.get(i).getTitle();
            }
            ArrayAdapter<String> sachAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, autoMaps);
            actTitle.setAdapter(sachAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = actTitle.getText().toString().trim();
                Maps maps = mapsDAO.selectMAPSByNameADR(name);
                try {
                    double latitude = maps.getLatitude();
                    double longtitude = maps.getLongtitude();


                    LatLng mapsLng = new LatLng(latitude, longtitude);
                    mMap.addMarker(new MarkerOptions().position(mapsLng).title(name + ""));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mapsLng));
                    edlongtitude.setText(longtitude + "");
                    edlatitude.setText(latitude + "");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("e loi du lieu", e + "");
                }

            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String title = actTitle.getText().toString().trim();
                Toast.makeText(MapsActivity.this, title, Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));


    }

    private void address() {
        Maps hotay = new Maps("Hồ Tây", 105.8062693, 21.0580711);
        Maps hohoankiem = new Maps("Hồ Hoàn Kiếm", 105.8501706, 21.0287797);
        Maps cs1_CDFPT = new Maps("Trường Cao đẳng thực hành FPT Polytechnic, CS1", 105.7630956, 21.0355605);
        Maps cs2_CDFPT = new Maps("Trường Cao đẳng thực hành FPT Polytechnic, CS2", 105.8017078, 21.0395869);
        Maps langbac = new Maps("Lăng chủ tịch Hồ Chí Minh", 105.8324506, 21.0367839);
        Maps timecity = new Maps("Khu đô thị Times City, Vĩnh Tuy, Hai Bà Trưng, Hà Nội", 105.8635068, 20.9943877);
        Maps rouyalcity = new Maps("Khu đô thị Royal City, Thượng Đình, Thanh Xuân, Hà Nội", 105.8130437, 21.002476);
        Maps svdMyDinh = new Maps("Sân vận động Quốc gia Mỹ Đình, Đường Lê Đức Thọ, Mỹ Đình 1, Nam Từ Liêm, Hà Nội", 105.7617706, 21.0204522);
        Maps sbNoiBai = new Maps("Sân bay Nội Bài, Sóc Sơn, Hà Nội", 105.8019768, 21.2187199);
        Maps bxMyDinh = new Maps("Bến xe Mỹ Đình, Mỹ Đình 2, Nam Từ Liêm, Hà Nội", 105.7760746, 21.0284347);
        Maps bxGiapBat = new Maps("Bến xe Giáp Bát, Giải Phóng, Hoàng Mai, Hà Nội", 105.8392603, 20.9802198);


//        boolean result1 = mapsDAO.insertMAPS(hotay);
//        boolean result2 = mapsDAO.insertMAPS(hohoankiem);
//        boolean result3 = mapsDAO.insertMAPS(cs1_CDFPT);
//        boolean result4 = mapsDAO.insertMAPS(cs2_CDFPT);
//        boolean result5 = mapsDAO.insertMAPS(langbac);
//        boolean result6 = mapsDAO.insertMAPS(timecity);
//        boolean result7 = mapsDAO.insertMAPS(rouyalcity);
//        boolean result8 = mapsDAO.insertMAPS(svdMyDinh);
//        boolean result9 = mapsDAO.insertMAPS(sbNoiBai);
//        boolean result10 = mapsDAO.insertMAPS(bxMyDinh);
//        boolean result11 = mapsDAO.insertMAPS(bxGiapBat);
    }

    public void AddMaps(View view) {

        mapsDAO = new MapsDAO(MapsActivity.this);
        String title = actTitle.getText().toString().trim();

        if (title.equals("")) {
            actTitle.setError("null");
            return;
        } else if (edlongtitude.getText().toString().trim().equals("")) {
            edlongtitude.setError("null");
            return;
        } else if (edlatitude.getText().toString().trim().equals("")) {
            edlatitude.setError("null");
            return;
        }else {
            Double longtitude = Double.parseDouble(edlongtitude.getText().toString().trim());
            Double latitude = Double.parseDouble(edlatitude.getText().toString().trim());
            Maps maps = new Maps(title, longtitude, latitude);
            if (mapsDAO.insertMAPS(maps)) {

                Toast.makeText(this, " them thanh cong  " + title, Toast.LENGTH_SHORT).show();
                LatLng mapsLng = new LatLng(latitude, longtitude);
                mMap.addMarker(new MarkerOptions().position(mapsLng).title(title));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mapsLng));

            } else {
                Toast.makeText(this, "that bai", Toast.LENGTH_SHORT).show();
            }
        }


    }


    public void EditMaps(View view) {
        mapsDAO = new MapsDAO(MapsActivity.this);
        String title = actTitle.getText().toString();
        Double longtitude = Double.parseDouble(edlongtitude.getText().toString());
        Double latitude = Double.parseDouble(edlatitude.getText().toString());
        Maps maps = new Maps(title, longtitude, latitude);

        if (mapsDAO.updateMAPS(maps)) {
            Toast.makeText(this, " sửa thành công " + title, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "sửa thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteMaps(View view) {
        String title = actTitle.getText().toString().trim();
        boolean result = mapsDAO.isDelete(title);


        if (result) {
            Toast.makeText(this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            edlatitude.setText("");
            edlongtitude.setText("");
            actTitle.setText("");
            mMap.clear();
//            Intent intent=getIntent();
//            startActivity(intent);
//
//
//
//            LatLng mapsLng = new LatLng(0,0);
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(mapsLng));

        } else {
            Toast.makeText(this, "delete false", Toast.LENGTH_SHORT).show();
        }
    }


    public void Cancel(View view) {
        edlatitude.setText("");
        edlongtitude.setText("");
        actTitle.setText("");
//        return;
    }

    public void ZoomIn(View view) {
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
    }

    public void ZoomOut(View view) {
        mMap.animateCamera(CameraUpdateFactory.zoomOut());
    }

    public void openList(View view) {

        Intent intent = new Intent(MapsActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
