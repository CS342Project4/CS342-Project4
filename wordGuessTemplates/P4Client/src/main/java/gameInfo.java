import java.io.Serializable;

public class gameInfo implements Serializable {
    String input,message,currentWord;
    int numGuesses,letterLocation;
    String letterInput;
    Boolean letterGuess,wordGuess,animalSelect,countrySelect,SuperheroSelect,correctGuess;
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setCorrectGuess(Boolean correctGuess) {
        this.correctGuess = correctGuess;
    }

    public void setLetterLocation(int letterLocation) {
        this.letterLocation = letterLocation;
    }

    public void setLetterInput(String letterInput) {
        this.letterInput = letterInput;
    }

    public void setNumGuesses(int numGuesses) {
        this.numGuesses = numGuesses;
    }

    public void setAnimalSelect(Boolean animalSelect) {
        this.animalSelect = animalSelect;
    }

    public void setCountrySelect(Boolean countrySelect) {
        this.countrySelect = countrySelect;
    }

    public void setSuperheroSelect(Boolean superheroSelect) {
        SuperheroSelect = superheroSelect;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }
}
