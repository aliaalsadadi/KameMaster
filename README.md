# KameMaster
big thanks to https://www.youtube.com/@NicholasRenotte.

A simple game where the color of your shot depends on how well you timed your charging and blasting stances.try making the model not detect anything you will get the color red.
Unfortunately I couldn't dockerize this game because my java files were compiled with a newer jdk than the dokcer java image. However you may play this game by reproducing the environment through:
1.setting up a conda env
2.settting up tensorflow object detection api in the conda env. (Really recommend Nicholas's videos as they are simple and straightforward)
3.modifying the code a little bit by changing the file paths 
(filepaths to change: in the Stats file change both the python.exe path to the python.exe of you conda env and the try.py will be cloned so you just 
have to copy its path and paste it,
you may need to chang the try.py a bit if it gives code error 1 you will have to change the path to the models checkpoint located in the ReaTimeObjectRecognition folder,
in the Game file change the gameSound filepath to your desired track)
that's it have fun!

