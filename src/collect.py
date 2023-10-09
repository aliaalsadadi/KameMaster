import cv2
import time
import uuid
import os

pos_path = 'images/'
print(os.path.exists(pos_path))
labels = ['charge','blast']
number_imgs = 30
for label in labels:
    print(f"collecting {label}")

    cap = cv2.VideoCapture(0)
    for i in range(number_imgs):
        ret, frame = cap.read()
        imagename = os.path.join(pos_path,label+'/'+'{}.jpg'.format(str(uuid.uuid1())))
        print(imagename)
        cv2.imwrite(imagename,frame)
        cv2.imshow("frame", frame)
        time.sleep(3)
        if cv2.waitKey(1) and 0xFF == ord('q'):
            break
    cap.release()