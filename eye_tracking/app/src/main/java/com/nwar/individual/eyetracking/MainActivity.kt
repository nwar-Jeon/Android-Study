package com.nwar.individual.eyetracking

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.MultiProcessor
import com.google.android.gms.vision.Tracker
import com.google.android.gms.vision.face.Face
import com.google.android.gms.vision.face.FaceDetector
import org.opencv.android.CameraBridgeViewBase
import org.opencv.core.Mat
import org.opencv.core.Core



class MainActivity : AppCompatActivity()/*, CameraBridgeViewBase.CvCameraViewListener2*/ {

    val CAMERA = 50
    val overlay by lazy {
        findViewById<GraphicOverlay>(R.id.overlay)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),CAMERA)
        }

        /*getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);

        val mOpenCvCameraView = findViewById<CameraBridgeViewBase>(R.id.activity_surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.setCameraIndex(1);*/
    }

    override fun onResume() {
        super.onResume()
        startCameraResource()
    }

    private fun createFaceDetector() : FaceDetector {
        val detector = FaceDetector.Builder(applicationContext)
            .setLandmarkType(FaceDetector.ALL_LANDMARKS)
            .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
            .setTrackingEnabled(true)
            .setMode(FaceDetector.FAST_MODE)
            .setProminentFaceOnly(true)
            .setMinFaceSize(0.35f)
            .build()

        val factory = MultiProcessor.Factory<Face>{
            FaceTracker(this,overlay)
        }
        val processor = MultiProcessor.Builder<Face>(factory).build()
        detector.setProcessor(processor)
        if(!detector.isOperational) {
            Toast.makeText(applicationContext,"라이브러리 다운로드가 안됐나봐요", Toast.LENGTH_SHORT).show()
        }
        return detector
    }

    fun startCameraResource() {
        val code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)
        if(code!=ConnectionResult.SUCCESS) {
            Toast.makeText(applicationContext,"코드가 success가 아니래요", Toast.LENGTH_SHORT).show()
        }

        val cameraSource = CameraSource.Builder(applicationContext, createFaceDetector())
            .setFacing(CameraSource.CAMERA_FACING_FRONT)
            .setRequestedFps(60.0f)
            .setRequestedPreviewSize(320,240)
            .setAutoFocusEnabled(true)
            .build()

        findViewById<CameraSourcePreview>(R.id.preview).start(cameraSource,overlay)
    }

    /*override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame?): Mat? {
        var result : Mat? = null
        if(inputFrame!=null) {
            val input = inputFrame.rgba()
            result = Mat(input.rows(), input.cols(), input.type())
        }
        return result
    }

    override fun onCameraViewStarted(width: Int, height: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraViewStopped() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            50 -> {}
        }
    }

}
