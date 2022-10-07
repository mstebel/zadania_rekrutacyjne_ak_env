enum Option {
    NEXT,
    EXIT;

    public static String getOptionsNames() {
        StringBuilder stringBuilder = new StringBuilder();
        Option[] options = values();
        for (int i = 0; i < options.length; i++) {
            stringBuilder.append(options[i].name().toLowerCase());
            if (i < options.length - 1) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
        return stringBuilder.toString();
    }

    public static void validateUserInput(String userInput) {
        boolean optionFound = false;
        int i = 0;
        Option[] options = values();
        while (!optionFound && i < options.length) {
            if (options[i].name().equals(userInput)) {
                optionFound = true;
            }
            i++;
        }
        if (!optionFound) {
            throw new IllegalArgumentException(getOptionNotFoundMessage());
        }
    }

    public static String getOptionNotFoundMessage() {
        return "Podana komenda jest nieprawidłowa. Spróbuj ponownie. Dostępne komendy: \n" +
                Option.getOptionsNames();
    }
}
