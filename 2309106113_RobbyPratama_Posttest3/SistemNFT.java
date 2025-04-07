import java.util.ArrayList;
import java.util.Scanner;

//Superclass User
abstract class User {
    protected String nama;

    public User(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public abstract void peran();
}

//Subclass Kreator
class Kreator extends User {
    public Kreator(String nama) {
        super(nama);
    }

    public NFT buatNFT(int id, String namaNFT, String deskripsi, double harga) {
        return new NFT(id, namaNFT, deskripsi, harga, this.nama);
    }

    @Override
    public void peran() {
        System.out.println(nama + " adalah Kreator dan dapat membuat NFT.");
    }
}

//Subclass Pembeli
class Pembeli extends User {
    public Pembeli(String nama) {
        super(nama);
    }

    public void beli(NFT nft) {
        if (!nft.getStatus().equalsIgnoreCase("Terjual")) {
            nft.setPemilik(this.nama);
            nft.setStatus("Terjual");
            System.out.println("NFT berhasil dibeli oleh " + this.nama + "!");
        } else {
            System.out.println("NFT sudah terjual!");
        }
    }

    @Override
    public void peran() {
        System.out.println(nama + " adalah Pembeli dan dapat membeli NFT.");
    }
}

//Class NFT
class NFT {
    private int id;
    private String namaNFT;
    private String deskripsi;
    private double harga;
    private String kreator;
    private String pemilik;
    private String status;

    public NFT(int id, String namaNFT, String deskripsi, double harga, String kreator) {
        this.id = id;
        this.namaNFT = namaNFT;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.kreator = kreator;
        this.pemilik = kreator;
        this.status = "Tersedia";
    }

    public int getId() {
        return id;
    }

    public String getNamaNFT() {
        return namaNFT;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public double getHarga() {
        return harga;
    }

    public String getKreator() {
        return kreator;
    }

    public String getPemilik() {
        return pemilik;
    }

    public String getStatus() {
        return status;
    }

    public void setNamaNFT(String namaNFT) {
        this.namaNFT = namaNFT;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public void setStatus(String status) {
        this.status = status;
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

//Main Class SistemNFT
public class SistemNFT {
    static ArrayList<NFT> daftarNFT = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static int idNFT = 1;

    public static void main(String[] args) {
        int pilih;
        do {
            System.out.println("\n===== Sistem Manajemen NFT =====");
            System.out.println("1. Tambah NFT (Kreator)");
            System.out.println("2. Lihat Daftar NFT");
            System.out.println("3. Edit NFT");
            System.out.println("4. Hapus NFT");
            System.out.println("5. Beli NFT (Pembeli)");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilih = input.nextInt();
            input.nextLine();

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

    static void tambahNFT() {
        System.out.println("\n=== Tambah NFT ===");
        System.out.print("Nama NFT     : ");
        String nama = input.nextLine();
        System.out.print("Deskripsi    : ");
        String deskripsi = input.nextLine();
        System.out.print("Harga        : ");
        double harga = input.nextDouble();
        input.nextLine();
        System.out.print("Nama Kreator : ");
        String namaKreator = input.nextLine();

        Kreator kreator = new Kreator(namaKreator);
        NFT nftBaru = kreator.buatNFT(idNFT++, nama, deskripsi, harga);
        daftarNFT.add(nftBaru);

        System.out.println("NFT berhasil ditambahkan oleh " + kreator.getNama() + "!");
    }

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

    static void editNFT() {
        lihatNFT();
        System.out.println("\n=== Edit NFT ===");
        System.out.print("Masukkan ID NFT yang ingin diedit: ");
        int id = input.nextInt();
        input.nextLine();

        NFT nftDitemukan = cariNFTById(id);

        if (nftDitemukan != null) {
            if (nftDitemukan.getStatus().equalsIgnoreCase("Terjual")) {
                System.out.println("NFT sudah terjual dan tidak bisa diedit!");
                return;
            }

            System.out.print("Nama NFT Baru (" + nftDitemukan.getNamaNFT() + "): ");
            String namaBaru = input.nextLine();
            System.out.print("Deskripsi Baru (" + nftDitemukan.getDeskripsi() + "): ");
            String deskripsiBaru = input.nextLine();
            System.out.print("Harga Baru (" + nftDitemukan.getHarga() + "): ");
            double hargaBaru = input.nextDouble();
            input.nextLine();

            if (!namaBaru.isEmpty()) nftDitemukan.setNamaNFT(namaBaru);
            if (!deskripsiBaru.isEmpty()) nftDitemukan.setDeskripsi(deskripsiBaru);
            if (hargaBaru >= 0) nftDitemukan.setHarga(hargaBaru);

            System.out.println("NFT berhasil diperbarui!");
        } else {
            System.out.println("NFT dengan ID tersebut tidak ditemukan.");
        }
    }

    static void hapusNFT() {
        lihatNFT();
        System.out.println("\n=== Hapus NFT ===");
        System.out.print("Masukkan ID NFT yang ingin dihapus: ");
        int id = input.nextInt();
        input.nextLine();

        boolean dihapus = daftarNFT.removeIf(nft -> nft.getId() == id);
        if (dihapus) {
            System.out.println("NFT berhasil dihapus!");
        } else {
            System.out.println("NFT dengan ID tersebut tidak ditemukan.");
        }
    }

    static void beliNFT() {
        lihatNFT();
        System.out.println("\n=== Beli NFT ===");
        System.out.print("Masukkan ID NFT yang ingin dibeli: ");
        int id = input.nextInt();
        input.nextLine();

        NFT nftDitemukan = cariNFTById(id);

        if (nftDitemukan != null) {
            if (nftDitemukan.getStatus().equalsIgnoreCase("Terjual")) {
                System.out.println("NFT ini sudah terjual!");
                return;
            }

            System.out.print("Nama Pembeli: ");
            String namaPembeli = input.nextLine();

            Pembeli pembeli = new Pembeli(namaPembeli);
            pembeli.beli(nftDitemukan);
        } else {
            System.out.println("NFT dengan ID tersebut tidak ditemukan.");
        }
    }

    static NFT cariNFTById(int id) {
        for (NFT nft : daftarNFT) {
            if (nft.getId() == id) {
                return nft;
            }
        }
        return null;
    }
}
