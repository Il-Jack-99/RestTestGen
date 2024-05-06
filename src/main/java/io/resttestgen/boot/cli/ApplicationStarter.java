package io.resttestgen.boot.cli;

import io.resttestgen.Database.Main;

public class ApplicationStarter {
    public static void main(String[] args) {
        //Run springboot with database
        Main.main(args);

        //Run CLI
        App.main(args);
    }
}
