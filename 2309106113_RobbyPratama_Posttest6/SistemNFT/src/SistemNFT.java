import java.util.ArrayList;
import java.util.Scanner;

// Interface: Info
interface Info {
    void tampilkanInfoLengkap();
    void tampilkanInfoSingkat();
}

// Abstract Class User
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

// Subclass Kreator
class Kreator extends User {
    public Kreator(String nama) {
        super(nama);
    }

    @Override
    public void peran() {
        System.out.println(nama + " adalah Kreator dan dapat membuat NFT.");
    }
}

// Subclass Pembeli
class Pembeli extends User {
    public Pembeli(String nama) {
        super(nama);
    }

    @Override
    public void peran() {
        System.out.println(nama + " adalah Pembeli dan dapat membeli NFT.");
    }
}

// Class NFT yang mengimplementasikan interface
class NFT implements Info {
    private int id;
    private String namaNFT;
    private String deskripsi;
    private double harga;
    private String kreator;
    private String pemilik;
    private String status;

    // static variable (dihitung total NFT)
    public static int totalNFT = 0;

    public NFT(int id, String namaNFT, String deskripsi, final double harga, String kreator) {
        this.id = id;
        this.namaNFT = namaNFT;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.kreator = kreator;
        this.pemilik = kreator;
        this.status = "Tersedia";
        totalNFT++;
    }

    public int getId() { return id; }
    public String getNamaNFT() { return namaNFT; }
    public String getDeskripsi() { return deskripsi; }
    public double getHarga() { return harga; }
    public String getKreator() { return kreator; }
    public String getPemilik() { return pemilik; }
    public String getStatus() { return status; }

    public void setNamaNFT(String namaNFT) { this.namaNFT = namaNFT; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setPemilik(String pemilik) { this.pemilik = pemilik; }
    public void setStatus(String status) { this.status = status; }

    // Interface methods
    @Override
    public void tampilkanInfoLengkap() {
        System.out.println("ID NFT     : " + id);
        System.out.println("Nama NFT   : " + namaNFT);
        System.out.println("Deskripsi  : " + deskripsi);
        System.out.println("Harga      : " + harga);
        System.out.println("Kreator    : " + kreator);
        System.out.println("Pemilik    : " + pemilik);
        System.out.println("Status     : " + status);
        System.out.println("---------------------------");
    }

    @Override
    public void tampilkanInfoSingkat() {
        System.out.println(namaNFT + " - Rp" + harga + " (" + status + ")");
    }
}

// Final class utama
public final class SistemNFT {
    static ArrayList<NFT> daftarNFT = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static int idNFT = 1;

    public static void main(String[] args) {
        int pilih = 0;

        do {
            try {
                System.out.println("\n===== Sistem NFT Marketplace =====");
                System.out.println("1. Tambah NFT (Kreator)");
                System.out.println("2. Lihat Daftar NFT");
                System.out.println("3. Edit NFT");
                System.out.println("4. Hapus NFT");
                System.out.println("5. Beli NFT (Pembeli)");
                System.out.println("6. Lihat Peran Pengguna");
                System.out.println("7. Lihat Total NFT (Static)");
                System.out.println("8. Keluar");
                System.out.print("Pilih menu: ");
                pilih = Integer.parseInt(input.nextLine());

                switch (pilih) {
                    case 1: tambahNFT(); break;
                    case 2: lihatNFT(); break;
                    case 3: editNFT(); break;
                    case 4: hapusNFT(); break;
                    case 5: beliNFT(); break;
                    case 6: lihatPeran(); break;
                    case 7: System.out.println("Total NFT yang terdaftar: " + NFT.totalNFT); break;
                    case 8: System.out.println("Terima kasih! Keluar..."); break;
                    default: System.out.println("Menu tidak tersedia.");
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid! Silakan coba lagi.");
                input.nextLine(); // clear buffer
            }
        } while (pilih != 8);
    }

    static void tambahNFT() {
        try {
            System.out.println("\n=== Tambah NFT ===");
            System.out.print("Nama Kreator: ");
            String namaKreator = input.nextLine();
            Kreator kreator = new Kreator(namaKreator);

            System.out.print("Nama NFT    : ");
            String namaNFT = input.nextLine();
            System.out.print("Deskripsi   : ");
            String deskripsi = input.nextLine();
            System.out.print("Harga       : ");
            double harga = Double.parseDouble(input.nextLine());

            NFT nftBaru = new NFT(idNFT++, namaNFT, deskripsi, harga, kreator.getNama());
            daftarNFT.add(nftBaru);
            System.out.println("NFT berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan NFT.");
        }
    }

    static void lihatNFT() {
        System.out.println("\n=== Daftar NFT ===");
        if (daftarNFT.isEmpty()) {
            System.out.println("Belum ada NFT yang tersedia.");
        } else {
            for (NFT nft : daftarNFT) {
                nft.tampilkanInfoSingkat();
            }
        }
    }

    static void editNFT() {
        try {
            lihatNFT();
            System.out.print("\nMasukkan ID NFT yang ingin diedit: ");
            int id = Integer.parseInt(input.nextLine());
            NFT nft = cariNFT(id);

            if (nft != null && !nft.getStatus().equalsIgnoreCase("Terjual")) {
                System.out.print("Nama Baru (" + nft.getNamaNFT() + "): ");
                String nama = input.nextLine();
                System.out.print("Deskripsi Baru (" + nft.getDeskripsi() + "): ");
                String deskripsi = input.nextLine();
                System.out.print("Harga Baru (" + nft.getHarga() + "): ");
                double harga = Double.parseDouble(input.nextLine());

                if (!nama.isEmpty()) nft.setNamaNFT(nama);
                if (!deskripsi.isEmpty()) nft.setDeskripsi(deskripsi);
                if (harga > 0) nft.setHarga(harga);

                System.out.println("NFT berhasil diperbarui.");
            } else {
                System.out.println("NFT tidak ditemukan atau sudah terjual.");
            }
        } catch (Exception e) {
            System.out.println("Gagal mengedit NFT.");
        }
    }

    static void hapusNFT() {
        try {
            System.out.print("\nMasukkan ID NFT yang ingin dihapus: ");
            int id = Integer.parseInt(input.nextLine());

            boolean dihapus = daftarNFT.removeIf(nft -> nft.getId() == id);
            if (dihapus) {
                NFT.totalNFT--;
                System.out.println("NFT berhasil dihapus.");
            } else {
                System.out.println("NFT tidak ditemukan.");
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus NFT.");
        }
    }

    static void beliNFT() {
        try {
            lihatNFT();
            System.out.print("\nMasukkan ID NFT yang ingin dibeli: ");
            int id = Integer.parseInt(input.nextLine());
            NFT nft = cariNFT(id);

            if (nft != null && nft.getStatus().equalsIgnoreCase("Tersedia")) {
                System.out.print("Nama Pembeli: ");
                String nama = input.nextLine();
                Pembeli pembeli = new Pembeli(nama);

                nft.setPemilik(pembeli.getNama());
                nft.setStatus("Terjual");

                System.out.println("NFT berhasil dibeli oleh " + pembeli.getNama() + "!");
            } else {
                System.out.println("NFT tidak tersedia atau sudah dibeli.");
            }
        } catch (Exception e) {
            System.out.println("Pembelian gagal.");
        }
    }

    static void lihatPeran() {
        try {
            System.out.print("Masukkan nama pengguna: ");
            String nama = input.nextLine();
            System.out.print("Apakah dia kreator? (y/n): ");
            String isKreator = input.nextLine();

            User user = isKreator.equalsIgnoreCase("y") ? new Kreator(nama) : new Pembeli(nama);
            user.peran();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan peran.");
        }
    }

    static NFT cariNFT(int id) {
        for (NFT nft : daftarNFT) {
            if (nft.getId() == id) {
                return nft;
            }
        }
        return null;
    }
}
