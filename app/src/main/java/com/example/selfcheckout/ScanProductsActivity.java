package com.example.selfcheckout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.media.AudioManager;
import android.media.Image;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ScanProductsActivity extends AppCompatActivity{
    private static String TAG = "ScanProductsActivity";
    private CameraDevice myCameraDevice;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    private SurfaceView surfaceView;
    private ToneGenerator toneGen1;
    private String barcodeData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_products);
        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC,     100);
        surfaceView = findViewById(R.id.surfaceView);
        final Handler handlerDelay = new Handler(Looper.getMainLooper());
        detectBarcode();

    }
    public void detectBarcode(){
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true)
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScanProductsActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScanProductsActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    surfaceView.post(new Runnable() {
                        @Override
                        public void run() {
                            barcodeData = barcodes.valueAt(0).displayValue;
                            System.out.println(barcodeData);
                            toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                        }
                    });
                }
            }
        });
    }


    //////////////////////
//    JavaCameraView javaCameraView;
//    Mat mRGB;
//    Mat gray;
//    Mat gradientX;
//    Mat gradientY;
//    BaseLoaderCallback myLoaderCallback = new BaseLoaderCallback(this) {
//        @Override
//        public void onManagerConnected(int status) {
//            switch(status){
//                case BaseLoaderCallback.SUCCESS:{
//                    javaCameraView.enableView();
//                    break;
//                }
//                default:{
//                    super.onManagerConnected(status);
//                    break;
//                }
//            }
//
//        }
//    };
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan_products);
//        javaCameraView = findViewById(R.id.camera);
//        ActivityCompat.requestPermissions(ScanProductsActivity.this,
//                new String[]{Manifest.permission.CAMERA},
//                1);
//        javaCameraView.setVisibility(SurfaceView.VISIBLE);
//        javaCameraView.setCameraPermissionGranted();
//        javaCameraView.setCvCameraViewListener(this);
//        if(mRGB != null){
//            detectBarcode(mRGB);
//        }
//
//    }
//
//        @Override
//        protected void onPause () {
//            super.onPause();
//            if (javaCameraView != null) {
//                javaCameraView.disableView();
//            }
//        }
//
//        @Override
//        protected void onDestroy () {
//
//            super.onDestroy();
//            if (javaCameraView != null) {
//                javaCameraView.disableView();
//            }
//        }
//        @Override
//        protected void onResume () {
//
//            super.onResume();
//            if (OpenCVLoader.initDebug()) {
//                System.out.println("OpenCV loaded successfully");
//                myLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
//            } else {
//                System.out.println("OpenCV not loaded");
//                OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, myLoaderCallback);
//            }
//        }
//
//        @Override
//        public void onCameraViewStarted ( int width, int height){
//            mRGB = new Mat(height, width, CvType.CV_8UC4);
//            gray = new Mat(height, width, CvType.CV_8UC1);
//            gradientX = new Mat(height, width, CvType.CV_8UC1);
//            gradientY = new Mat(height, width, CvType.CV_8UC1);
//        }
//
//        @Override
//        public void onCameraViewStopped () {
//            mRGB.release();
//        }
//
//        @Override
//        public Mat onCameraFrame (CameraBridgeViewBase.CvCameraViewFrame inputFrame){
//            mRGB = inputFrame.rgba();
//            Mat gradient = new Mat();
//            Mat gradientAbs = new Mat();
//            Mat blurred = new Mat();
//            Mat threshold = new Mat(mRGB.height(), mRGB.width(), CvType.CV_8UC1);
//            Imgproc.cvtColor(mRGB,gray,Imgproc.COLOR_RGB2GRAY);
//            Imgproc.Sobel(gray, gradientX, CvType.CV_64F, 1, 0,-1);
//            Imgproc.Sobel(gray, gradientY, CvType.CV_64F, 0, 1,-1);
//            Core.subtract(gradientX,gradientY,gradient);
//            Core.convertScaleAbs(gradient,gradientAbs);
//            Imgproc.medianBlur(gradientAbs,blurred,9);
//            Imgproc.threshold(blurred,threshold,225,255,Imgproc.THRESH_BINARY);
//
//            Mat se = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new Size(100,7));
//            Mat closedBarcode = new Mat(mRGB.height(), mRGB.width(), CvType.CV_8UC1);
//            Imgproc.morphologyEx(threshold,closedBarcode,Imgproc.MORPH_CLOSE,se);
//
//            Imgproc.erode(closedBarcode, closedBarcode, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(100,7)));
//            Imgproc.dilate(closedBarcode,closedBarcode,Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(100,7)));
//            final List<MatOfPoint> points = new ArrayList<>();
//            final Mat hierarchy = new Mat();
//            Imgproc.findContours(closedBarcode,points,hierarchy,Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);
//
//            for (int i = 0; i < points.size(); i++) {
//                Imgproc.drawContours(mRGB,points,i,new Scalar(0,255,0));
//            }
//            return mRGB;
//        }
//        public void detectBarcode(Mat mRGB){
//
//
//
//        }
    }
