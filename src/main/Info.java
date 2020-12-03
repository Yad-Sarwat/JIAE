
/**
 *
 * @author Yad Sarwat Mustafa
 */
public class Info {

    public final String allInfo = "Welcome to JIAE (Java Independent Analysis Engine)!\n"
            + "Develeped by: Yad Sarwat Mustafa\n"
            + "The criteria of my system is that it is an analyzing engine that analyze an image by storing its "
            + "data into a three dimensional array and provides many algorithms and equations to handle its data "
            + "and returns the output cleanly and efficiently. Beside its main intension that this system has been "
            + "created for, it also provides a very simple and friendly compiler known as (Java-Commander) that "
            + "communicates with the user and acknowledge them the status of the system and their input actions "
            + "whether it has been succeeded or not. Plus the obvious GUI provided as well that is the easiest to "
            + "use but does not provide full access like the Java-Commander does.\n\n"
            + "Java Commander compiler takes the following commands:\n\n"
            + "jsystem\n"
            + "jpath\n"
            + "jdate\n"
            + "jtime\n"
            + "jsave\n"
            + "jclear\n"
            + "jmkdir\n"
            + "jruntime\n"
            + "jimage\n\n"
            + "Note: that some of the commands does not work alone but needs to have an argument assigned to them.\n"
            + "Make sure that you have full information about the commands before using them.\n\n"
            + "Note: you  can type in the command name followed by ? to get full information about that specific command!\n"
            + "Example:\njsystem ?\n";

    public final String jsystem = "jsystem return the name of the current operating System that this system is running on!\n";

    public final String jpath = "jpath return the current Path or Directory that the System is executing in.";

    public final String jdate = "jdate returns the Date of today in Day-Month-Year format.\n"
            + "Arranged in Calendar style to emulate calendar UI that allow detecting the weeks plus the day names as well.\n";

    public final String jtime = "jtime returns current time in Hour : Minute : Second format. Note that the Hour is in 24 digit notation.\n";

    public final String jsave = "jsave is a command that takes at least one sub-argument, maximum 4 sub-arguments.\n"
            + "This command saves any image displayed on this system with or without effects and filters.\n"
            + "It requires file name to save the file by that name, Note the file must be PNG or JPG.\n"
            + "its sub-arguments consists of: -n = File_Name, -p = Path\n"
            + "Note: Without using -n,-p you can save image only like: (jsave File_Name Path) order,\n"
            + "using -n,-p will allow you to swap places anytime, like: (jsave -n File_Name -p Path) or (jsave -p Path -n File_Name)\n"
            + "Note: you can ignore Path sub-argument and the output file will be automatically saved in (jpath) Path.\n"
            + "For more information about jpath, type: jpath ?\n"
            + "Other ways you can write jsave:\n"
            + "1--> jsave File_Name\n"
            + "2--> jsave -n File_Name\n"
            + "3--> jsave File_Name Path\n"
            + "4--> jsave File_Name -p Path\n"
            + "5--> jsave -n File_Name Path\n"
            + "6--> jsave -n File_Name -p Path\n"
            + "7--> jsave -p Path -n File_Name\n";

    public final String jclear = "jclear as its name suggests, it clears the JText_TextField (this box that you are reading from!)"
            + "that stores and displays the Previous commands.";

    public final String jmkdir = "jmkdir is used to make new directories (folders).Note that jmkdir does not work alone, but they require arguments."
            + "If no path specified, the System will automatically create "
            + "the folder name in the same directory that the system is currently running on (jpath).\n"
            + "jmkdir is taking sub-arguments like \"-p\" to clarify that the next argument will be Path given, "
            + "use –p only when you are specifying the Path or directory to be created.\njmkdir is used in the following ways: \n"
            + "1--> jmkdir Folder_Name\n"
            + "2--> jmkdir Path+Folder_Name\n"
            + "3--> jmkdir -p Path+Folder_Name";

    public final String jruntime = "jruntime returns the total time that this system has been running from the"
            + " very moment it started executing till now.";

    public final String jimage = "jimage is the most complex word that has the most arguments and sub-arguments like –p, -loop and integers."
            + " the arguments are: \n"
            + "setimg\n"
            + "reset\n"
            + "filter\n"
            + "oilpaint\n"
            + "blur\n"
            + "getred\n"
            + "getgreen,\n"
            + "getblue\n"
            + "add\n"
            + "addgreen\n"
            + "addblue\n"
            + "addred"
            + "Note: jimage alone does not work, but it require argument(s)"
            + "Note: you can save your edited image using (jsave) command.\n"
            + "Note: it is best practice to use (setimg) first because most of other arguments works after (setimg) has "
            + "been used.\n"
            + "Note: since jimage arguments are too much and some of them are complex, you have the option to get"
            + "full information about each one of them seperately! For example type in :\n"
            + "jimage setimg ?";

    public final String setimg = "setimg is the command that sets image to the Canvas. using this command will opens a dialog so that you can"
            + " direct the system to fetch an image file for the Canvas to dispay. note that the Canvas will automaticaly resizes the selected"
            + " image to fit it with its current size";

    public final String reset="reset will reset the image to the original form, the very image that has been set using (setimg)";
    
    public final String filter = "filter argument will filter the image based on the rgb color that users will input. "
            + "If the RGB value is available, the color will be returned in that specified area else it will return black. "
            + "This filter does not work with loop sub-argument because the (filter function itself)"
            + " works using multi loops, also it is the filter that is having the most types of input styles that "
            + "each differs depending on what the users are inputting.\n"
            + "Here is how to write filter command in jimage:\n"
            + "•\tjimage filter r 12 (filters the image where red = 12, ignoring the values of green and blue).\n"
            + "•\tjimage filter g 12 (filters the image where green = 12, ignoring the values of red and blue).\n"
            + "•\tjimage filter b 12 (filters the image where blue = 12, ignoring the values of red and green).\n"
            + "•\tjimage filter r 2 g 2 (filters the image where red = 2 and green = 2, ignoring the value of  blue).\n"
            + "•\tjimage filter r 2 b 2 (filters the image where red = 2 and blue = 2, ignoring the value of  green).\n"
            + "•\tjimage filter g 2 b 2 (filters the image where green = 2 and blue = 2, ignoring the value of  red).\n"
            + "•\tjimage filter r 2 g 2 b 2 (filters the image where red = 2 and green = 2 and blue = 2, note the more "
            + "specific you are the fewer pixels will be retuned).\n"
            + "•\tjimage filter r 2_50  (filters the image where red = 2 till red = 50 so the image is looping 48 times"
            + " each with a different red value to find. Ignoring green and blue values). \n"
            + "•\tjimage filter g 10_50  (filters the image where green = 10 till green = 50 so the image is looping"
            + " 40 times each with a different green value to find. Ignoring red and blue values).\n"
            + "•\tjimage filter b 2_200  (filters the image where blue = 2 till blue = 200 so the image is looping 198"
            + " times each with a different blue value to find. Ignoring red and green values).\n"
            + "•\tjimage filter r 2_50 g 2 (filters the image where red = 2 and green =2 till red = 50 and green = 2"
            + " so the image is looping 48 times each with a different red value to find but green has to be 2. Ignoring blue values).\n"
            + "•\tjimage filter r 2_50 b 12 (filters the image where red = 2 and blue =12 till red = 50 and blue = 12 so "
            + "the image is looping 48 times each with a different red value to find but blue has to be 12. Ignoring green values).\n"
            + "•\tjimage filter r 2_50 g 2 b 12 (filters the image where red = 2 and green =2 and blue = 12, till red = 50"
            + " and green = 2 and blue = 12 so the image is looping 48 times each with a different red value to find but"
            + " green has to be 2 and blue has to be 12).\n"
            + "•\tjimage filter r 12 g 50_125 (filters the image where green = 50 and red = 12 till green =  125 and red"
            + " = 12 so the image is looping 75 times each with a different green value to find but red has to be 12. "
            + "Ignoring blue values).\n"
            + "•\tjimage filter g 50_125 b 14 (filters the image where green = 50 and blue = 14 till green =  125 and "
            + "blue = 12 so the image is looping 75 times each with a different green value to find but blue has"
            + " to be 14. Ignoring red values).\n"
            + "•\tjimage filter r 12 g 50_125 b 14 (filters the image where green = 50 and red = 12 and blue= 14,"
            + " till green =  125 and blue = 14 and red = 12 so the image is looping 75 times each with a different"
            + " green value to find but red has to be 12 and blue = 14).\n"
            + "•\tjimage filter r 12 b 125_200 (filters the image 75 times (200-125=75) with each time blue is one"
            + " number between 125 to 200 plus red has to be 12 as well, ignoring green values). \n"
            + "•\tjimage filter g 15 b 125_200 (filters the image 75 times (200-125=75) with each time blue is one"
            + " number between 125 to 200 plus green has to be 15 as well, ignoring red values).\n"
            + "•\tjimage filter r 12 g 15 b 125_200 (filters the image 75 times (200-125=75) with each time"
            + " blue is one number between 125 to 200 plus red has to be 12  and green = 15as well).\n"
            + "•\tjimage filter r 0_75 g 75_250 (filters the image 75 times (75-0=75)  for red and filters "
            + "the image 175 times (250-75=175) for green ignoring blue)\n"
            + "•\tjimage filter r 0_75 g 75_250 b 12 250 (filters the image 75 times (75-0=75)  for red and filters "
            + "the image 175 times (250-75=175) for green for both of the filtration blue must be 12 or return black).\n"
            + "•\tjimage filter r 0_75 b 75_250 (filters the image 75 times (75-0=75)  for red and filters the image 175"
            + " times (250-75=175) for blue ignoring green)\n"
            + "•\tjimage filter b 0_75 g 15 b 75_250 (filters the image 75 times (75-0=75)  for red and filters the image 175 times "
            + "(250-75=175) for blue for both of the filtration green must be 15 or return black).\n"
            + "•\tJimage filter r 0_100 g 10_150 b 175_255 (the most complex filter, this filter returns the result of (100x140x100)"
            + " times looping through the image and the output will be pixels in between the ranges of"
            + " (0<=r<=100 && 10<=g<=150 &&175<=b<=255 ) ).\n"
            + "Note: you can flip the RGB values when you are writing the commands, we chose the RGB values as standard and"
            + " to ease the understanding of the concepts. You can type in (rgb, gbr, bgr, grb, brg.. etc). The Java Commander "
            + "compiler is structured in a smart way that can recognize the numbers assigned to which colors coordinate.";

    public final String oilpaint = "oilpaint argument type will add some glaring effect to the image, giving it a classical"
            + " style oil painting effect (as its name suggests). It does support using loop as well. It works similarly to blur:\n"
            + "•\tjimage oilpaint (oilpaint once)\n"
            + "•\tjimage oilpaint 12 (oilpaint using loop indirectly 12 times)\n"
            + "•\tjimage oilpaint –loop 2 (oilpaint using loop indirectly 2 times)";

    public final String blur = "blur argument will blur the image by a kernel of 3x3 pixel size so the effect is very light."
            + " Fortunately you can use loop with this effect, (loop is another feature that jimage supports to allow users get "
            + "the result they require directly rather than re-typing it over and over again). Here is how blur command works with jimage:\n"
            + "•\tjimage blur (blur once)\n"
            + "•\tjimage blur 12 (blur using loop indirectly 12 times)\n"
            + "•\tjimage blur –loop 4 (blue using loop directly 4 times)";

    public final String getRed = "getred argument returns the full-scaled red color of the image (0_255),"
            + " but only of red meaning it will set green and blue to 0 at all pixels. ";

    public final String getGreen = "getgreen argument returns the full-scaled green color of the image (0_255),"
            + " but only of green meaning it will set red and blue to 0 at all pixels. ";

    public final String getBlue = "getblue argument returns the full-scaled blue color of the image (0_255),"
            + " but only of blue meaning it will set red and green to 0 at all pixels. ";

    public final String add = "add argument is an argument of jimage. It can take two sub-arguments as well."
            + "The sub-arguments that they take are ratio and"
            + " file paths, when you add two images you have to have two images!\n"
            + "Note: majority of jimage arguments works when there is already an instantiated image (jimage setimg)."
            + " Therefore the best practice is that you define an image first then start to apply changes."
            + " Else the system will alert you about the previous sentences. \n"
            + "In our scenario when the jimage has already been instantiated and we want to add another image to it,"
            + " the next instruction step can be done through three different steps that are all similar:\n"
            + "1-->(jimage add setimg), know that setimg opens a dialog for the user to select an image.\n"
            + "For more information, type:\n"
            + "jimage setimg ?\n"
            + "This command works similar, it tells the system to open a dialog and add what image file the user will select"
            + " to the first image that has been selected.\n"
            + "2-->(jimage add ImagePath), similar to jsave it will automatically get the last argument as the path "
            + "(makes some filtrations about it first) and then fetch it to the processor to add or mix the image with previous one.\n"
            + "3-->(jimage add –p ImagePath), similar to point 2 it does the same thing but adding that –p makes the "
            + "system more accurate about fetching the next line directly as path thus the results will arrive faster.\n"
            + "Note: when you declared the image files to be added through the previous steps, the default ration factor will be \n(0.5) "
            + "for both of the images so it will be half by half. You can change the ratio as much as you like by simply typing the ratio"
            + " number directly, the following lines will be some examples to clarify the process: \n"
            + "•\t(jimage add setimg 0.1) this will get the second image file through a dialog and adds it to the first image "
            + "by the following ratio(first image 0.9 + second image 0.1).\n"
            + "•\t(jimage add C:\\Users\\UserName\\Desktop\\image.jpg 0.2) , this will get the second image file "
            + "directly and adds it to the first image by the following ratio(first image 0.8 + second image 0.2).\n"
            + "•\t(jimage add –p C:\\Users\\UserName\\Desktop\\image.jpg 0.8) , this will get the second image file "
            + "directly (fastest) and adds it to the first image by the following ratio(first image * 0.2 + second image  * 0.8).";

    public final String addRed = "addred will add another Image to (Only Red Pixels chanel) of the image ignoring green and blue colors\n"
            + "addred works exactly as (add).\n If you want to know how to use it correctly type in:\n"
            + "jimage add ?";

    public final String addGreen = "addGreen will add another Image to (Only Green Pixels chanel) of the image ignoring red and blue colors\n"
            + "addgreen works exactly as (add).\n If you want to know how to use it correctly type in:\n"
            + "jimage add ?";

    public final String addBlue = "addBlue will add another Image to (Only Blue Pixels chanel) of the image ignoring red and green colors\n"
            + "addblue works exactly as (add).\n If you want to know how to use it correctly type in:\n"
            + "jimage add ?";
}
