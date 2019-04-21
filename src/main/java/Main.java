import FirstScenario.FileSeparator;
import SecondScenario.RegExSearch;
import ServiceClasses.MyProperty;
import ThirdScenario.AddDelimiters;

class Main {
    public static void main(String[] args) {
        argIInterpreter(args);
    }
    private static void argIInterpreter(String[] args) {
        if (args.length > 1) System.out.println("You enter more then one argument");

        if (args[0].equals("-f")) {
            MyProperty.initF();
            FileSeparator.separateAndSave();
        }
        else if (args[0].equals("-s")) {
            MyProperty.initS();
            RegExSearch.begin();
        }
        else if (args[0].equals("-t")) {
            MyProperty.initT();
            AddDelimiters.delimitAndSave();
        }
        else if (args[0].toLowerCase().equals("-h") || args[0].toLowerCase().equals("-help")) {
            System.out.println("Hello!\n" +
                    "This is a simpleJavaApplication, it may does three things:" +
                    "\nSplit file into multiple files of 20,000 lines" +
                    "\nCreates a new file from lines that contain the keyword" +
                    "\nCreates a file where all values are separated by the desired delimiter" +
                    "\n\n In this program you may enter:" +
                    "\n-f\tfor execute first task" +
                    "\n-s\tfor execute second task" +
                    "\n-t\tfor execute third task");
        }
        else System.out.println("That command is not exist, enter -h for help");
    }
}
