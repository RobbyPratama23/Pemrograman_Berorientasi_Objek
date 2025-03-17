import java.util.ArrayList;
import java.util.Scanner;

// Class NFT
class NFT {
    int id;
    String namaNFT;
    String deskripsi;
    double harga;
    String kreator;
    String pemilik;
    String status;

    //konstruktor
    public NFT(int id, String namaNFT, String deskripsi, double harga, String kreator) {
        this.id = id;
        this.namaNFT = namaNFT;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.kreator = kreator;
        this.pemilik = kreator;
        this.status = "Tersedia";
    }

    public void display() {
        System.out.println("ID NFT     : " + id);
        System.out.println("Nama NFT   : " + namaNFT);
        System.out.println("Deskripsi  : " + deskripsi);
        System.out.println("Harga      : " + harga);
        System.out.println("Kreator    : " + kreator);
        System.out.println("Pemilik    : " + pemilik);
        System.out.println("Status     : " + status);
        System.out.println("---------------------------");
    }
}

public class SistemNFT {
    static ArrayList<NFT> daftarNFT = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static int idNFT = 1;

    public static void main(String[] args) {
        int pilih;
        do {
            System.out.println("\n===== Sistem Manajemen NFT =====");
            System.out.println("1. Tambah NFT");
            System.out.println("2. Lihat Daftar NFT");
            System.out.println("3. Edit NFT");
            System.out.println("4. Hapus NFT");
            System.out.println("5. Beli NFT");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilih = input.nextInt();
            input.nextLine(); // Clear buffer

            switch (pilih) {
                case 1:
                    tambahNFT();
                    break;
                case 2:
                    lihatNFT();
                    break;
                case 3:
                    editNFT();
                    break;
                case 4:
                    hapusNFT();
                    break;
                case 5:
                    beliNFT();
                    break;
                case 6:
                    System.out.println("Keluar dari sistem...");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia!");
            }
        } while (pilih != 6);
    }

    // CREATE NFT
    static void tambahNFT() {
        System.out.println("\n=== Tambah NFT ===");
        System.out.print("Nama NFT     : ");
        String nama = input.nextLine();
        System.out.print("Deskripsi    : ");
        String deskripsi = input.nextLine();
        System.out.print("Harga        : ");
        double harga = input.nextDouble();
        input.nextLine(); // Clear buffer
        System.out.print("Nama Kreator : ");
        String kreator = input.nextLine();

        NFT nftBaru = new NFT(idNFT++, nama, deskripsi, harga, kreator);
        daftarNFT.add(nftBaru);

        System.out.println("NFT berhasil ditambahkan!");
    }

    // READ NFT
    static void lihatNFT() {
        System.out.println("\n=== Daftar NFT ===");
        if (daftarNFT.isEmpty()) {
            System.out.println("Belum ada NFT yang terdaftar.");
        } else {
            for (NFT nft : daftarNFT) {
                nft.display();
            }
        }
    }

    // UPDATE NFT
    static void editNFT() {
        lihatNFT();
        System.out.println("\n=== Edit NFT ===");
        System.out.print("Masukkan ID NFT yang ingin diedit: ");
        int id = input.nextInt();
        input.nextLine(); // Clear buffer

        NFT nftDitemukan = null;
        for (NFT nft : daftarNFT) {
            if (nft.id == id) {
                nftDitemukan = nft;
                break;
            }
        }

        if (nftDitemukan != null) {
            System.out.print("Nama NFT Baru (" + nftDitemukan.namaNFT + "): ");
            String namaBaru = input.nextLine();
            System.out.print("Deskripsi Baru (" + nftDitemukan.deskripsi + "): ");
            String deskripsiBaru = input.nextLine();
            System.out.print("Harga Baru (" + nftDitemukan.harga + "): ");
            double hargaBaru = input.nextDouble();
            input.nextLine();

            nftDitemukan.namaNFT = namaBaru;
            nftDitemukan.deskripsi = deskripsiBaru;
            nftDitemukan.harga = hargaBaru;

            System.out.println("NFT berhasil diperbarui!");
        } else {
            System.out.println("NFT dengan ID tersebut tidak ditemukan.");
        }
    }

    // DELETE NFT
    static void hapusNFT() {
        System.out.println("\n=== Hapus NFT ===");
        System.out.print("Masukkan ID NFT yang ingin dihapus: ");
        int id = input.nextInt();
        input.nextLine(); // Clear buffer

        boolean dihapus = daftarNFT.removeIf(nft -> nft.id == id);
        if (dihapus) {
            System.out.println("NFT berhasil dihapus!");
        } else {
            System.out.println("NFT dengan ID tersebut tidak ditemukan.");
        }
    }

    // BELI NFT
    static void beliNFT() {
        System.out.println("\n=== Beli NFT ===");
        System.out.print("Masukkan ID NFT yang ingin dibeli: ");
        int id = input.nextInt();
        input.nextLine(); // Clear buffer

        NFT nftDitemukan = null;
        for (NFT nft : daftarNFT) {
            if (nft.id == id) {
                nftDitemukan = nft;
                break;
            }
        }

        if (nftDitemukan != null) {
            if (nftDitemukan.status.equalsIgnoreCase("Terjual")) {
                System.out.println("NFT ini sudah terjual!");
                return;
            }

            System.out.print("Nama Pembeli: ");
            String pembeli = input.nextLine();

            nftDitemukan.pemilik = pembeli;
            nftDitemukan.status = "Terjual";

            System.out.println("NFT berhasil dibeli oleh " + pembeli + "!");
        } else {
            System.out.println("NFT dengan ID tersebut tidak ditemukan.");
        }
    }
}
