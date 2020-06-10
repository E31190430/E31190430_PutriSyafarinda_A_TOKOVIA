-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Jun 2020 pada 07.48
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_minimarket`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_barang`
--

CREATE TABLE `tabel_barang` (
  `kode_barang` varchar(6) NOT NULL,
  `nama_barang` varchar(200) NOT NULL,
  `satuan_barang` varchar(20) NOT NULL,
  `stok_barang` int(20) NOT NULL,
  `harga_barang` int(100) NOT NULL,
  `kode_kategori` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_barang`
--

INSERT INTO `tabel_barang` (`kode_barang`, `nama_barang`, `satuan_barang`, `stok_barang`, `harga_barang`, `kode_kategori`) VALUES
('B001', 'Mie Indomie Goreng', 'PCS', 82, 2500, 'KTG01'),
('B002', 'Minyak Goreng', 'Botol', 39, 5000, 'KTG01'),
('B003', 'Teh Gelas', 'Botol', 89, 1000, 'KTG01'),
('B004', 'Wardah', 'PCS', 43, 20000, 'KTG03'),
('B005', 'Pomade Staylish', 'Buah', 21, 15000, 'KTG03'),
('B006', 'Aqua', 'Botol', 70, 3000, 'KTG01');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_detailtransaksi`
--

CREATE TABLE `tabel_detailtransaksi` (
  `kode_transaksi` varchar(10) NOT NULL,
  `jumlah_stok` int(50) NOT NULL,
  `subtotal` int(100) NOT NULL,
  `tanggal` datetime NOT NULL,
  `kode_barang` varchar(6) CHARACTER SET latin1 NOT NULL,
  `no` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tabel_detailtransaksi`
--

INSERT INTO `tabel_detailtransaksi` (`kode_transaksi`, `jumlah_stok`, `subtotal`, `tanggal`, `kode_barang`, `no`) VALUES
('KT001', 2, 2000, '2020-06-04 10:56:22', 'B003', 33),
('KT001', 2, 5000, '2020-06-04 10:56:31', 'B001', 34),
('KT002', 20, 300000, '2020-06-06 12:09:43', 'B005', 35);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_infotransaksi`
--

CREATE TABLE `tabel_infotransaksi` (
  `kode_transaksi` varchar(10) NOT NULL,
  `jumlah_stok` int(20) NOT NULL,
  `sub_total` int(100) NOT NULL,
  `tanggal` datetime NOT NULL,
  `kode_barang` varchar(6) CHARACTER SET latin1 NOT NULL,
  `id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_karyawan`
--

CREATE TABLE `tabel_karyawan` (
  `kode_pegawai` int(4) NOT NULL,
  `nm_pegawai` varchar(100) NOT NULL,
  `kelamin` enum('L','W') NOT NULL,
  `alamat` text NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `pass_id` varchar(50) NOT NULL,
  `jabatan` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_karyawan`
--

INSERT INTO `tabel_karyawan` (`kode_pegawai`, `nm_pegawai`, `kelamin`, `alamat`, `user_id`, `pass_id`, `jabatan`) VALUES
(1, 'putri', 'W', 'bondowoso', 'admin', 'admin', 'Admin'),
(2, 'syafarinda', 'W', 'Bondowoso', 'Kasir', 'kasir', 'Kasir');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_kategori`
--

CREATE TABLE `tabel_kategori` (
  `kode_kategori` varchar(5) NOT NULL,
  `nama_kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_kategori`
--

INSERT INTO `tabel_kategori` (`kode_kategori`, `nama_kategori`) VALUES
('KTG01', 'makanan'),
('KTG02', 'Minuman'),
('KTG03', 'Kosmetik');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_transaksi`
--

CREATE TABLE `tabel_transaksi` (
  `kode_transaksi` varchar(10) NOT NULL,
  `tanggal_jual` date NOT NULL,
  `total` int(50) NOT NULL,
  `cash` int(50) NOT NULL,
  `kembali` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tabel_transaksi`
--

INSERT INTO `tabel_transaksi` (`kode_transaksi`, `tanggal_jual`, `total`, `cash`, `kembali`) VALUES
('KT001', '2020-06-04', 7000, 8000, 1000),
('KT002', '2020-06-06', 300000, 600000, 300000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tabel_barang`
--
ALTER TABLE `tabel_barang`
  ADD PRIMARY KEY (`kode_barang`),
  ADD KEY `kd_kategori` (`kode_kategori`);

--
-- Indeks untuk tabel `tabel_detailtransaksi`
--
ALTER TABLE `tabel_detailtransaksi`
  ADD PRIMARY KEY (`no`),
  ADD KEY `kode_transaksi` (`kode_transaksi`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indeks untuk tabel `tabel_infotransaksi`
--
ALTER TABLE `tabel_infotransaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indeks untuk tabel `tabel_karyawan`
--
ALTER TABLE `tabel_karyawan`
  ADD PRIMARY KEY (`kode_pegawai`);

--
-- Indeks untuk tabel `tabel_kategori`
--
ALTER TABLE `tabel_kategori`
  ADD PRIMARY KEY (`kode_kategori`);

--
-- Indeks untuk tabel `tabel_transaksi`
--
ALTER TABLE `tabel_transaksi`
  ADD PRIMARY KEY (`kode_transaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tabel_detailtransaksi`
--
ALTER TABLE `tabel_detailtransaksi`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT untuk tabel `tabel_infotransaksi`
--
ALTER TABLE `tabel_infotransaksi`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tabel_barang`
--
ALTER TABLE `tabel_barang`
  ADD CONSTRAINT `tabel_barang_ibfk_1` FOREIGN KEY (`kode_kategori`) REFERENCES `tabel_kategori` (`kode_kategori`);

--
-- Ketidakleluasaan untuk tabel `tabel_detailtransaksi`
--
ALTER TABLE `tabel_detailtransaksi`
  ADD CONSTRAINT `tabel_detailtransaksi_ibfk_2` FOREIGN KEY (`kode_barang`) REFERENCES `tabel_barang` (`kode_barang`),
  ADD CONSTRAINT `tabel_detailtransaksi_ibfk_3` FOREIGN KEY (`kode_transaksi`) REFERENCES `tabel_transaksi` (`kode_transaksi`);

--
-- Ketidakleluasaan untuk tabel `tabel_infotransaksi`
--
ALTER TABLE `tabel_infotransaksi`
  ADD CONSTRAINT `tabel_infotransaksi_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `tabel_barang` (`kode_barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
