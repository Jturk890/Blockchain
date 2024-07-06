import java.security.MessageDigest;
import java.util.ArrayList;
class Block  {
    private String minerPseudonym;
    private String previousHash;
    private String hash;
    private long nonce;

    // Constructor
    public Block(String previousHash, String minerPseudonym) {
        this.previousHash = previousHash;
        this.minerPseudonym = minerPseudonym;
        this.nonce = 0; // Initial nonce value
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = previousHash + minerPseudonym + nonce;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    // Getters and Setters
    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}

public class ToyChain {

    public static void main(String[] args) {

        ArrayList<Block> blockchain = new ArrayList<Block>();

        Block firstBlock = new Block( "dac401d5516f31781a343788e16c7e6aa38fa6743d7a2be2ba8c2ca9e4efe62e", "Caesar");
        blockchain.add(firstBlock);

        Block secondBlock = new Block("095773ea71ff0e5152801adf927be43bbe3a7a36e7c3d66e318ad3986de05ce1", "Caesar");
        blockchain.add(secondBlock);

        Block thirdBlock = new Block("00c65202c0ffbd56e6d5c70397429bf75a1a05c5d0495a63b3df33451d7cf588", "Caesar");
        blockchain.add(thirdBlock);

        Block fourthBlock = new Block("000473165efb68b60599e96df6596b5909824ae829aa48546c177cf131ebcab6", "Caesar");
        blockchain.add(fourthBlock);

        Block fifthBlock = new Block("000000620a9f684778738b849ead91bbfe7597ff055fbae591239cafe31f32c6", "Caesar");
        blockchain.add(fifthBlock);

        Block sixthBlock = new Block("0000000ff015422f525150de5c80e6bce75103ba5d5288f7dd326e5b55960ed5", "Caesar");
        blockchain.add(sixthBlock);

        Block seventhBlock = new Block("00000000e1f73bf28e60afc6039d834b026c768789d5d52ea139b2145b1c5b7d", "Caesar");
        blockchain.add(seventhBlock);

        Block eighthBlock = new Block("0000000000dda0f4ccacd4137f6650427aa648d4d6e7acd7c3cdba99d05915b8", "Caesar");
        blockchain.add(eighthBlock);
        System.out.println("Previous Hash for block 8 : " + eighthBlock.getPreviousHash());
        System.out.println("Hash for block 8 : " + eighthBlock.getHash());
        seventhBlock.mineBlock(11); // 11 zeroes
        System.out.println("The nonce is: " + eighthBlock.getNonce());
    }
}
