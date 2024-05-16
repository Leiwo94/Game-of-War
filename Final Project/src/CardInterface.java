/**
 * 
 */
public interface CardInterface
{
    enum Suit
    {
        clubs, diamonds, hearts, spades
    };

    public String toString();

    public boolean set(char value, Suit suit);

    public boolean equals(Card card);

    public char getValue();

    public Suit getSuit();

    public boolean isErrorFlag();
}
