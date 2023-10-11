import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import java.nio.ByteBuffer;
import static org.bytedeco.opencv.global.opencv_core.flip;
public class HelloCV {
    public static void main(String[] args) {
        // Create a new video capture instance
        VideoCapture videoCapture = new VideoCapture(0); // 0 represents the default webcam

        // Check if the video capture is successfully opened
        if (!videoCapture.isOpened()) {
            System.out.println("Failed to open the webcam.");
            return;
        }

        // Create a new canvas frame to display the webcam feed
        CanvasFrame canvasFrame = new CanvasFrame("Webcam", 1.0); // Set the gamma value manually

        // Read frames from the webcam until the window is closed
        Mat frame = new Mat();
        while (canvasFrame.isVisible() && videoCapture.read(frame)) {
            // Flip the frame horizontally for correct orientation
            flip(frame, frame, 1);

            // Convert the OpenCV Mat to a JavaCV Frame
            Frame javaCvFrame = new Frame(frame.cols(), frame.rows(), Frame.DEPTH_UBYTE, frame.channels());
            ByteBuffer buffer = frame.createBuffer();
            javaCvFrame.image[0] = buffer;

            // Display the frame in the canvas frame
            canvasFrame.showImage(javaCvFrame);
        }

        // Release the video capture and close the canvas frame
        videoCapture.release();
        canvasFrame.dispose();
    }
}