-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 31/03/2024 às 22:18
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `autor`
--

CREATE TABLE `autor` (
  `ID` int(11) NOT NULL,
  `NOME` varchar(70) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cidade`
--

CREATE TABLE `cidade` (
  `ID` int(11) NOT NULL,
  `DESCRICAO` varchar(30) DEFAULT NULL,
  `ESTADO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `CPF` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL,
  `NOME` varchar(70) DEFAULT NULL,
  `NUM_LIVROS_EMPRESTADOS` int(11) DEFAULT NULL,
  `CALOTEIRO` tinyint(1) DEFAULT NULL,
  `ID_FUNCIONARIO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `emprestimo`
--

CREATE TABLE `emprestimo` (
  `ID_LIVRO` int(11) DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  `ID_FUNCIONARIO` int(11) DEFAULT NULL,
  `DATA_EMPRESTIMO` date DEFAULT NULL,
  `DATA_DEVOLUCAO` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `estado`
--

CREATE TABLE `estado` (
  `ID` int(11) NOT NULL,
  `DESCRICAO` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `CPF` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL,
  `NOME` varchar(70) DEFAULT NULL,
  `SENHA` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `genero`
--

CREATE TABLE `genero` (
  `ID` int(11) NOT NULL,
  `DESCRICAO` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `livro`
--

CREATE TABLE `livro` (
  `ID` int(11) NOT NULL,
  `DESCRICAO` varchar(50) DEFAULT NULL,
  `QTD_ESTOQUE` int(11) DEFAULT NULL,
  `FUNCIONARIO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `livros_autores`
--

CREATE TABLE `livros_autores` (
  `ID_LIVRO` int(11) DEFAULT NULL,
  `ID_AUTOR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `livros_generos`
--

CREATE TABLE `livros_generos` (
  `ID_LIVRO` int(11) DEFAULT NULL,
  `ID_GENERO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`ID`);

--
-- Índices de tabela `cidade`
--
ALTER TABLE `cidade`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ESTADO` (`ESTADO`);

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FUNCIONARIO` (`ID_FUNCIONARIO`);

--
-- Índices de tabela `emprestimo`
--
ALTER TABLE `emprestimo`
  ADD KEY `ID_LIVRO` (`ID_LIVRO`),
  ADD KEY `ID_CLIENTE` (`ID_CLIENTE`),
  ADD KEY `ID_FUNCIONARIO` (`ID_FUNCIONARIO`);

--
-- Índices de tabela `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`ID`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`ID`);

--
-- Índices de tabela `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`ID`);

--
-- Índices de tabela `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FUNCIONARIO` (`FUNCIONARIO`);

--
-- Índices de tabela `livros_autores`
--
ALTER TABLE `livros_autores`
  ADD KEY `ID_LIVRO` (`ID_LIVRO`),
  ADD KEY `ID_AUTOR` (`ID_AUTOR`);

--
-- Índices de tabela `livros_generos`
--
ALTER TABLE `livros_generos`
  ADD KEY `ID_LIVRO` (`ID_LIVRO`),
  ADD KEY `ID_GENERO` (`ID_GENERO`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `autor`
--
ALTER TABLE `autor`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cidade`
--
ALTER TABLE `cidade`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `estado`
--
ALTER TABLE `estado`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `genero`
--
ALTER TABLE `genero`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `livro`
--
ALTER TABLE `livro`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `cidade`
--
ALTER TABLE `cidade`
  ADD CONSTRAINT `cidade_ibfk_1` FOREIGN KEY (`ESTADO`) REFERENCES `estado` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrições para tabelas `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`ID_FUNCIONARIO`) REFERENCES `funcionario` (`ID`) ON UPDATE CASCADE;

--
-- Restrições para tabelas `emprestimo`
--
ALTER TABLE `emprestimo`
  ADD CONSTRAINT `emprestimo_ibfk_1` FOREIGN KEY (`ID_LIVRO`) REFERENCES `livro` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `emprestimo_ibfk_2` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `emprestimo_ibfk_3` FOREIGN KEY (`ID_FUNCIONARIO`) REFERENCES `funcionario` (`ID`) ON UPDATE CASCADE;

--
-- Restrições para tabelas `livro`
--
ALTER TABLE `livro`
  ADD CONSTRAINT `livro_ibfk_1` FOREIGN KEY (`FUNCIONARIO`) REFERENCES `funcionario` (`ID`) ON UPDATE CASCADE;

--
-- Restrições para tabelas `livros_autores`
--
ALTER TABLE `livros_autores`
  ADD CONSTRAINT `livros_autores_ibfk_1` FOREIGN KEY (`ID_LIVRO`) REFERENCES `livro` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `livros_autores_ibfk_2` FOREIGN KEY (`ID_AUTOR`) REFERENCES `autor` (`ID`) ON UPDATE CASCADE;

--
-- Restrições para tabelas `livros_generos`
--
ALTER TABLE `livros_generos`
  ADD CONSTRAINT `livros_generos_ibfk_1` FOREIGN KEY (`ID_LIVRO`) REFERENCES `livro` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `livros_generos_ibfk_2` FOREIGN KEY (`ID_GENERO`) REFERENCES `genero` (`ID`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
