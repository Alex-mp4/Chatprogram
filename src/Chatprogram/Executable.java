package Chatprogram;

import Chatprogram.Client.ClientController;
import Chatprogram.Server.ServerController;

import java.io.IOException;

public class Executable {
    public static void main(String[] args) {
        ServerController.main(null);
        ClientController.main(null);

    }
}
