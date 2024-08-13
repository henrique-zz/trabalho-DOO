package metodos;
import javax.swing.text.*;

public class LimitadorCaractere extends PlainDocument {
    private int maxLength;

    public LimitadorCaractere(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) {
            return;
        }

        // Remove any non-digit characters
        string = string.replaceAll("\\D", ""); //retira todos os caracteres não númericos

        if ((getLength() + string.length()) <= maxLength) {
            super.insertString(offset, string, attr);
        }
    }

    @Override
    public void replace(int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) {
            return;
        }

        // Remove any non-digit characters
        string = string.replaceAll("\\D", "");

        if ((getLength() + string.length() - length) <= maxLength) {
            super.replace(offset, length, string, attr);
        }
    }
}
