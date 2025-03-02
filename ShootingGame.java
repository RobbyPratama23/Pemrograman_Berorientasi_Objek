// Program ini adalah Shooting Game, yaitu program menembak sederhana yang alur permainan nya akan di lakukan secara random.

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

// Kelas Senjata
class Weapon {
    String name;
    int damage;

    // Konstruktor
    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }
}

// Kelas Armor
class Armor {
    String name;
    int defense;

    // Konstruktor
    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }
}

// Kelas Karakter
class Character {
    private String name;
    private int health;
    private int damage;
    private int armor;

    // Konstruktor
    public Character(String name, int damage, int armor) {
        this.name = name;
        this.health = 100;
        this.damage = damage;
        this.armor = armor;
    }

    // Setter dan Getter
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        int reducedDamage = Math.max(0, damage - armor);
        health -= reducedDamage;
        if (health < 0) health = 0;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void displayStatus() {
        System.out.println(name + " | Health: " + health + " | Damage: " + damage + " | Armor: " + armor);
    }

    public void resetHealth() {
        this.health = 100;
    }
    

}

public class ShootingGame {
    private static ArrayList<Character> characters = new ArrayList<>();
    private static ArrayList<Weapon> weapons = new ArrayList<>();
    private static ArrayList<Armor> armors = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initializeItems();
        boolean running = true;

        // Akan Looping sampai memilih keluar dari program
        while (running) {
            System.out.println("\n=== Shooting Game ===");
            System.out.println("1. Tambah Karakter");
            System.out.println("2. Lihat Karakter");
            System.out.println("3. Perbarui Karakter");
            System.out.println("4. Hapus Karakter");
            System.out.println("5. Mulai Pertarungan");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addCharacter();
                    break;
                case 2:
                    viewCharacters();
                    break;
                case 3:
                    updateCharacter();
                    break;
                case 4:
                    deleteCharacter();
                    break;
                case 5:
                    startBattle();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // List data Senjata dan Armor
    private static void initializeItems() {
        weapons.add(new Weapon("Pistol", 20));
        weapons.add(new Weapon("Rifle", 35));
        weapons.add(new Weapon("Shotgun", 50));

        armors.add(new Armor("Rompi Ringan", 5));
        armors.add(new Armor("Rompi Sedang", 15));
        armors.add(new Armor("Rompi Berat", 25));
    }

    private static void addCharacter() {
        System.out.print("Masukkan nama karakter: ");
        String name = scanner.nextLine();

        System.out.println("Pilih Weapon:");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + ". " + weapons.get(i).name + " (Damage: " + weapons.get(i).damage + ")");
        }
        System.out.print("Pilih nomor weapon: ");
        int weaponIndex = scanner.nextInt() - 1;
        
        System.out.println("Pilih Armor:");
        for (int i = 0; i < armors.size(); i++) {
            System.out.println((i + 1) + ". " + armors.get(i).name + " (Armor: " + armors.get(i).defense + ")");
        }
        System.out.print("Pilih nomor armor: ");
        int armorIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        characters.add(new Character(name, weapons.get(weaponIndex).damage, armors.get(armorIndex).defense));
        System.out.println("Karakter berhasil ditambahkan!");
    }

    private static void viewCharacters() {
        if (characters.isEmpty()) {
            System.out.println("Tidak ada karakter tersedia.");
        } else {
            System.out.println("\n=== Daftar Karakter ===");
            for (int i = 0; i < characters.size(); i++) {
                System.out.print((i + 1) + ". ");
                characters.get(i).displayStatus();
            }
        }
    }

    private static void deleteCharacter() {
        viewCharacters();
        if (characters.isEmpty()) return;
        System.out.print("Pilih karakter yang ingin dihapus: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < characters.size()) {
            characters.remove(index);
            System.out.println("Karakter berhasil dihapus.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private static void updateCharacter() {
        viewCharacters();
        if (characters.isEmpty()) return;
        System.out.print("Pilih karakter yang ingin diperbarui: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (index >= 0 && index < characters.size()) {
            System.out.println("Pilih Weapon baru:");
            for (int i = 0; i < weapons.size(); i++) {
                System.out.println((i + 1) + ". " + weapons.get(i).name + " (Damage: " + weapons.get(i).damage + ")");
            }
            System.out.print("Pilih nomor weapon: ");
            int weaponIndex = scanner.nextInt() - 1;
            
            System.out.println("Pilih Armor baru:");
            for (int i = 0; i < armors.size(); i++) {
                System.out.println((i + 1) + ". " + armors.get(i).name + " (Armor: " + armors.get(i).defense + ")");
            }
            System.out.print("Pilih nomor armor: ");
            int armorIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            
            characters.get(index).setDamage(weapons.get(weaponIndex).damage);
            characters.get(index).setArmor(armors.get(armorIndex).defense);
            System.out.println("Karakter berhasil diperbarui!");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private static void startBattle() {
    if (characters.size() < 2) {
        System.out.println("Minimal harus ada 2 karakter untuk bertarung!");
        return;
    }

    viewCharacters();
    System.out.print("Pilih karakter pemain: ");
    int playerIndex = scanner.nextInt() - 1;
    System.out.print("Pilih karakter musuh: ");
    int enemyIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (playerIndex < 0 || playerIndex >= characters.size() || enemyIndex < 0 || enemyIndex >= characters.size()) {
        System.out.println("Pilihan tidak valid!");
        return;
    }

    Character player = characters.get(playerIndex);
    Character enemy = characters.get(enemyIndex);

    // Reset health sebelum bertarung agar bisa digunakan kembali
    player.resetHealth();
    enemy.resetHealth();

    System.out.println("\nPertarungan dimulai antara " + player.getName() + " dan " + enemy.getName() + "!");

    while (player.isAlive() && enemy.isAlive()) {
        int attackerChance = random.nextInt(100); // Nilai acak antara 0 - 99
        if (attackerChance < 50) { // 50% peluang menyerang lebih dinamis
            System.out.println("\n" + player.getName() + " menyerang " + enemy.getName());
            enemy.takeDamage(player.getDamage());
        } else {
            System.out.println("\n" + enemy.getName() + " menyerang " + player.getName());
            player.takeDamage(enemy.getDamage());
        }

        // Tampilkan sisa health
        System.out.println(player.getName() + " memiliki sisa health: " + player.getHealth());
        System.out.println(enemy.getName() + " memiliki sisa health: " + enemy.getHealth());

        if (!enemy.isAlive()) {
            System.out.println("\n" + enemy.getName() + " telah kalah!");
            break;
        } else if (!player.isAlive()) {
            System.out.println("\n" + player.getName() + " telah kalah!");
            break;
        }
    }

    System.out.println("\nPertarungan selesai!");

    // Reset Health
    player.resetHealth();
    enemy.resetHealth();
}


}
