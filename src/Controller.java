import com.sun.javafx.scene.control.skin.Utils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.io.ByteArrayInputStream;

public class Controller {

    @FXML
    private ImageView currentFrame;

    @FXML
    private Button start_btn;

    @FXML
    void startCamera(ActionEvent event) {
        VideoCapture capture = new VideoCapture();
        capture.open(0);

        Mat frame = new Mat();
        capture.read(frame);
        Imgproc.cvtColor(frame,frame,Imgproc.COLOR_BGR2GRAY);
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png",frame,buffer);
        new Image(new ByteArrayInputStream(buffer.toArray()));

    }

}

