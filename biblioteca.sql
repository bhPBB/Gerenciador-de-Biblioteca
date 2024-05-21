PGDMP  &    	                |         
   biblioteca    16.2    16.2 .    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    26013 
   biblioteca    DATABASE     �   CREATE DATABASE biblioteca WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE biblioteca;
                postgres    false            �            1259    26014    autor    TABLE     W   CREATE TABLE public.autor (
    id integer NOT NULL,
    nome character varying(40)
);
    DROP TABLE public.autor;
       public         heap    postgres    false            �            1259    26017    autor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.autor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.autor_id_seq;
       public          postgres    false    215            �           0    0    autor_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.autor_id_seq OWNED BY public.autor.id;
          public          postgres    false    216            �            1259    26018    cliente    TABLE     4  CREATE TABLE public.cliente (
    cpf character varying(14) NOT NULL,
    nome character varying(50),
    num_livros_emprestados integer DEFAULT 0,
    caloteiro boolean DEFAULT false,
    id_funcionario character varying(14),
    cep character varying(9),
    email character varying(50),
    foto bytea
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    26023 
   emprestimo    TABLE       CREATE TABLE public.emprestimo (
    id_livro integer,
    id_cliente character varying(20),
    id_funcionario character varying(20),
    data_emprestimo date,
    data_devolucao date,
    status character varying(9) DEFAULT 'Em aberto'::character varying
);
    DROP TABLE public.emprestimo;
       public         heap    postgres    false            �            1259    26027    funcionario    TABLE     �   CREATE TABLE public.funcionario (
    cpf character varying(14) NOT NULL,
    email character varying(50),
    nome character varying(50),
    senha text,
    foto bytea
);
    DROP TABLE public.funcionario;
       public         heap    postgres    false            �            1259    26032    genero    TABLE     ]   CREATE TABLE public.genero (
    id integer NOT NULL,
    descricao character varying(20)
);
    DROP TABLE public.genero;
       public         heap    postgres    false            �            1259    26035    genero_id_seq    SEQUENCE     �   CREATE SEQUENCE public.genero_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.genero_id_seq;
       public          postgres    false    220            �           0    0    genero_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.genero_id_seq OWNED BY public.genero.id;
          public          postgres    false    221            �            1259    26036    livro    TABLE     �   CREATE TABLE public.livro (
    id integer NOT NULL,
    descricao character varying(50),
    qtd_estoque integer,
    id_funcionario character varying(14),
    imagem bytea
);
    DROP TABLE public.livro;
       public         heap    postgres    false            �            1259    26041    livro_id_seq    SEQUENCE     �   CREATE SEQUENCE public.livro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.livro_id_seq;
       public          postgres    false    222            �           0    0    livro_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.livro_id_seq OWNED BY public.livro.id;
          public          postgres    false    223            �            1259    26042    livros_autores    TABLE     S   CREATE TABLE public.livros_autores (
    id_livro integer,
    id_autor integer
);
 "   DROP TABLE public.livros_autores;
       public         heap    postgres    false            �            1259    26045    livros_generos    TABLE     T   CREATE TABLE public.livros_generos (
    id_livro integer,
    id_genero integer
);
 "   DROP TABLE public.livros_generos;
       public         heap    postgres    false            8           2604    26048    autor id    DEFAULT     d   ALTER TABLE ONLY public.autor ALTER COLUMN id SET DEFAULT nextval('public.autor_id_seq'::regclass);
 7   ALTER TABLE public.autor ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            <           2604    26049 	   genero id    DEFAULT     f   ALTER TABLE ONLY public.genero ALTER COLUMN id SET DEFAULT nextval('public.genero_id_seq'::regclass);
 8   ALTER TABLE public.genero ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220            =           2604    26050    livro id    DEFAULT     d   ALTER TABLE ONLY public.livro ALTER COLUMN id SET DEFAULT nextval('public.livro_id_seq'::regclass);
 7   ALTER TABLE public.livro ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222            �          0    26014    autor 
   TABLE DATA           )   COPY public.autor (id, nome) FROM stdin;
    public          postgres    false    215   5       �          0    26018    cliente 
   TABLE DATA           q   COPY public.cliente (cpf, nome, num_livros_emprestados, caloteiro, id_funcionario, cep, email, foto) FROM stdin;
    public          postgres    false    217   !5       �          0    26023 
   emprestimo 
   TABLE DATA           s   COPY public.emprestimo (id_livro, id_cliente, id_funcionario, data_emprestimo, data_devolucao, status) FROM stdin;
    public          postgres    false    218   Z5       �          0    26027    funcionario 
   TABLE DATA           D   COPY public.funcionario (cpf, email, nome, senha, foto) FROM stdin;
    public          postgres    false    219   w5       �          0    26032    genero 
   TABLE DATA           /   COPY public.genero (id, descricao) FROM stdin;
    public          postgres    false    220   �5       �          0    26036    livro 
   TABLE DATA           S   COPY public.livro (id, descricao, qtd_estoque, id_funcionario, imagem) FROM stdin;
    public          postgres    false    222   �5       �          0    26042    livros_autores 
   TABLE DATA           <   COPY public.livros_autores (id_livro, id_autor) FROM stdin;
    public          postgres    false    224   6       �          0    26045    livros_generos 
   TABLE DATA           =   COPY public.livros_generos (id_livro, id_genero) FROM stdin;
    public          postgres    false    225   *6       �           0    0    autor_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.autor_id_seq', 1, false);
          public          postgres    false    216            �           0    0    genero_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.genero_id_seq', 1, false);
          public          postgres    false    221            �           0    0    livro_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.livro_id_seq', 1, false);
          public          postgres    false    223            ?           2606    26052    autor autor_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.autor DROP CONSTRAINT autor_pkey;
       public            postgres    false    215            A           2606    26054    cliente cliente_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cpf);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    217            C           2606    26056    funcionario funcionario_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (cpf);
 F   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_pkey;
       public            postgres    false    219            E           2606    26058    genero genero_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.genero DROP CONSTRAINT genero_pkey;
       public            postgres    false    220            G           2606    26060    livro livro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.livro DROP CONSTRAINT livro_pkey;
       public            postgres    false    222            H           2606    26061 #   cliente cliente_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_id_funcionario_fkey;
       public          postgres    false    219    4675    217            I           2606    26066 %   emprestimo emprestimo_id_cliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(cpf) ON UPDATE CASCADE;
 O   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_cliente_fkey;
       public          postgres    false    4673    218    217            J           2606    26071 )   emprestimo emprestimo_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 S   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_funcionario_fkey;
       public          postgres    false    219    4675    218            K           2606    26076 #   emprestimo emprestimo_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_livro_fkey;
       public          postgres    false    218    4679    222            L           2606    26081    livro livro_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 I   ALTER TABLE ONLY public.livro DROP CONSTRAINT livro_id_funcionario_fkey;
       public          postgres    false    222    4675    219            M           2606    26086 +   livros_autores livros_autores_id_autor_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_autores
    ADD CONSTRAINT livros_autores_id_autor_fkey FOREIGN KEY (id_autor) REFERENCES public.autor(id) ON UPDATE CASCADE;
 U   ALTER TABLE ONLY public.livros_autores DROP CONSTRAINT livros_autores_id_autor_fkey;
       public          postgres    false    4671    215    224            N           2606    26091 +   livros_autores livros_autores_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_autores
    ADD CONSTRAINT livros_autores_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id);
 U   ALTER TABLE ONLY public.livros_autores DROP CONSTRAINT livros_autores_id_livro_fkey;
       public          postgres    false    224    4679    222            O           2606    26096 ,   livros_generos livros_generos_id_genero_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_generos
    ADD CONSTRAINT livros_generos_id_genero_fkey FOREIGN KEY (id_genero) REFERENCES public.genero(id) ON UPDATE CASCADE;
 V   ALTER TABLE ONLY public.livros_generos DROP CONSTRAINT livros_generos_id_genero_fkey;
       public          postgres    false    220    4677    225            P           2606    26101 +   livros_generos livros_generos_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_generos
    ADD CONSTRAINT livros_generos_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id);
 U   ALTER TABLE ONLY public.livros_generos DROP CONSTRAINT livros_generos_id_livro_fkey;
       public          postgres    false    225    222    4679            �      x������ � �      �   )   x�342�3�`]C#N �i������H�*������ �f^      �      x������ � �      �   L   x�-��� ���D�&R���K�';�,��?�f�\�U�({i�Ϋx"�&c `d�Z+*O���M�"�v=E      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     