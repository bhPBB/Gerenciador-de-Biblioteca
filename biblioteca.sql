PGDMP      7                |         
   biblioteca    16.2    16.2 ?               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    25504 
   biblioteca    DATABASE     �   CREATE DATABASE biblioteca WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE biblioteca;
                postgres    false            �            1259    25505    autor    TABLE     W   CREATE TABLE public.autor (
    id integer NOT NULL,
    nome character varying(40)
);
    DROP TABLE public.autor;
       public         heap    postgres    false            �            1259    25508    autor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.autor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.autor_id_seq;
       public          postgres    false    215                       0    0    autor_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.autor_id_seq OWNED BY public.autor.id;
          public          postgres    false    216            �            1259    25509    cidade    TABLE     q   CREATE TABLE public.cidade (
    id integer NOT NULL,
    descricao character varying(30),
    estado integer
);
    DROP TABLE public.cidade;
       public         heap    postgres    false            �            1259    25512    cidade_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cidade_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.cidade_id_seq;
       public          postgres    false    217                       0    0    cidade_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.cidade_id_seq OWNED BY public.cidade.id;
          public          postgres    false    218            �            1259    25513    cliente    TABLE       CREATE TABLE public.cliente (
    cpf character varying(14) NOT NULL,
    nome character varying(50),
    num_livros_emprestados integer DEFAULT 0,
    caloteiro boolean DEFAULT false,
    id_funcionario character varying(14),
    cidade integer,
    estado integer
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    25518 
   emprestimo    TABLE     �   CREATE TABLE public.emprestimo (
    id_livro integer,
    id_cliente character varying(20),
    id_funcionario character varying(20),
    data_emprestimo date,
    data_devolucao date
);
    DROP TABLE public.emprestimo;
       public         heap    postgres    false            �            1259    25521    estado    TABLE     ]   CREATE TABLE public.estado (
    id integer NOT NULL,
    descricao character varying(30)
);
    DROP TABLE public.estado;
       public         heap    postgres    false            �            1259    25524    estado_id_seq    SEQUENCE     �   CREATE SEQUENCE public.estado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.estado_id_seq;
       public          postgres    false    221            	           0    0    estado_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.estado_id_seq OWNED BY public.estado.id;
          public          postgres    false    222            �            1259    25525    funcionario    TABLE     �   CREATE TABLE public.funcionario (
    cpf character varying(14) NOT NULL,
    email character varying(50),
    nome character varying(50),
    senha text
);
    DROP TABLE public.funcionario;
       public         heap    postgres    false            �            1259    25530    genero    TABLE     ]   CREATE TABLE public.genero (
    id integer NOT NULL,
    descricao character varying(20)
);
    DROP TABLE public.genero;
       public         heap    postgres    false            �            1259    25533    genero_id_seq    SEQUENCE     �   CREATE SEQUENCE public.genero_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.genero_id_seq;
       public          postgres    false    224            
           0    0    genero_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.genero_id_seq OWNED BY public.genero.id;
          public          postgres    false    225            �            1259    25534    livro    TABLE     �   CREATE TABLE public.livro (
    id integer NOT NULL,
    descricao character varying(50),
    qtd_estoque integer,
    id_funcionario character varying(14),
    imagem bytea
);
    DROP TABLE public.livro;
       public         heap    postgres    false            �            1259    25539    livro_id_seq    SEQUENCE     �   CREATE SEQUENCE public.livro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.livro_id_seq;
       public          postgres    false    226                       0    0    livro_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.livro_id_seq OWNED BY public.livro.id;
          public          postgres    false    227            �            1259    25540    livros_autores    TABLE     S   CREATE TABLE public.livros_autores (
    id_livro integer,
    id_autor integer
);
 "   DROP TABLE public.livros_autores;
       public         heap    postgres    false            �            1259    25543    livros_generos    TABLE     T   CREATE TABLE public.livros_generos (
    id_livro integer,
    id_genero integer
);
 "   DROP TABLE public.livros_generos;
       public         heap    postgres    false            B           2604    25557    autor id    DEFAULT     d   ALTER TABLE ONLY public.autor ALTER COLUMN id SET DEFAULT nextval('public.autor_id_seq'::regclass);
 7   ALTER TABLE public.autor ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            C           2604    25558 	   cidade id    DEFAULT     f   ALTER TABLE ONLY public.cidade ALTER COLUMN id SET DEFAULT nextval('public.cidade_id_seq'::regclass);
 8   ALTER TABLE public.cidade ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            F           2604    25559 	   estado id    DEFAULT     f   ALTER TABLE ONLY public.estado ALTER COLUMN id SET DEFAULT nextval('public.estado_id_seq'::regclass);
 8   ALTER TABLE public.estado ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221            G           2604    25560 	   genero id    DEFAULT     f   ALTER TABLE ONLY public.genero ALTER COLUMN id SET DEFAULT nextval('public.genero_id_seq'::regclass);
 8   ALTER TABLE public.genero ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224            H           2604    25561    livro id    DEFAULT     d   ALTER TABLE ONLY public.livro ALTER COLUMN id SET DEFAULT nextval('public.livro_id_seq'::regclass);
 7   ALTER TABLE public.livro ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    226            �          0    25505    autor 
   TABLE DATA           )   COPY public.autor (id, nome) FROM stdin;
    public          postgres    false    215   �F       �          0    25509    cidade 
   TABLE DATA           7   COPY public.cidade (id, descricao, estado) FROM stdin;
    public          postgres    false    217   G       �          0    25513    cliente 
   TABLE DATA           o   COPY public.cliente (cpf, nome, num_livros_emprestados, caloteiro, id_funcionario, cidade, estado) FROM stdin;
    public          postgres    false    219   sH       �          0    25518 
   emprestimo 
   TABLE DATA           k   COPY public.emprestimo (id_livro, id_cliente, id_funcionario, data_emprestimo, data_devolucao) FROM stdin;
    public          postgres    false    220   �H       �          0    25521    estado 
   TABLE DATA           /   COPY public.estado (id, descricao) FROM stdin;
    public          postgres    false    221   �H       �          0    25525    funcionario 
   TABLE DATA           >   COPY public.funcionario (cpf, email, nome, senha) FROM stdin;
    public          postgres    false    223   �I       �          0    25530    genero 
   TABLE DATA           /   COPY public.genero (id, descricao) FROM stdin;
    public          postgres    false    224    J       �          0    25534    livro 
   TABLE DATA           S   COPY public.livro (id, descricao, qtd_estoque, id_funcionario, imagem) FROM stdin;
    public          postgres    false    226   =J       �          0    25540    livros_autores 
   TABLE DATA           <   COPY public.livros_autores (id_livro, id_autor) FROM stdin;
    public          postgres    false    228   ZJ                  0    25543    livros_generos 
   TABLE DATA           =   COPY public.livros_generos (id_livro, id_genero) FROM stdin;
    public          postgres    false    229   wJ                  0    0    autor_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.autor_id_seq', 1, false);
          public          postgres    false    216                       0    0    cidade_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cidade_id_seq', 28, true);
          public          postgres    false    218                       0    0    estado_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.estado_id_seq', 27, true);
          public          postgres    false    222                       0    0    genero_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.genero_id_seq', 1, false);
          public          postgres    false    225                       0    0    livro_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.livro_id_seq', 1, false);
          public          postgres    false    227            J           2606    25565    autor autor_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.autor DROP CONSTRAINT autor_pkey;
       public            postgres    false    215            L           2606    25567    cidade cidade_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.cidade DROP CONSTRAINT cidade_pkey;
       public            postgres    false    217            N           2606    25569    cliente cliente_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cpf);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    219            P           2606    25571    estado estado_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.estado DROP CONSTRAINT estado_pkey;
       public            postgres    false    221            R           2606    25573    funcionario funcionario_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (cpf);
 F   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_pkey;
       public            postgres    false    223            T           2606    25575    genero genero_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.genero DROP CONSTRAINT genero_pkey;
       public            postgres    false    224            V           2606    25577    livro livro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.livro DROP CONSTRAINT livro_pkey;
       public            postgres    false    226            W           2606    25582    cidade cidade_estado_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_estado_fkey FOREIGN KEY (estado) REFERENCES public.estado(id) ON UPDATE CASCADE ON DELETE CASCADE;
 C   ALTER TABLE ONLY public.cidade DROP CONSTRAINT cidade_estado_fkey;
       public          postgres    false    221    217    4688            X           2606    25587    cliente cliente_cidade_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cidade_fkey FOREIGN KEY (cidade) REFERENCES public.cidade(id) ON UPDATE CASCADE;
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_cidade_fkey;
       public          postgres    false    217    219    4684            Y           2606    25592    cliente cliente_estado_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_estado_fkey FOREIGN KEY (estado) REFERENCES public.estado(id) ON UPDATE CASCADE;
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_estado_fkey;
       public          postgres    false    219    4688    221            Z           2606    25597 #   cliente cliente_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_id_funcionario_fkey;
       public          postgres    false    4690    219    223            [           2606    25602 %   emprestimo emprestimo_id_cliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(cpf) ON UPDATE CASCADE;
 O   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_cliente_fkey;
       public          postgres    false    219    4686    220            \           2606    25607 )   emprestimo emprestimo_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 S   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_funcionario_fkey;
       public          postgres    false    223    4690    220            ]           2606    25612 #   emprestimo emprestimo_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_livro_fkey;
       public          postgres    false    226    220    4694            ^           2606    25617    livro livro_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 I   ALTER TABLE ONLY public.livro DROP CONSTRAINT livro_id_funcionario_fkey;
       public          postgres    false    223    226    4690            _           2606    25622 +   livros_autores livros_autores_id_autor_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_autores
    ADD CONSTRAINT livros_autores_id_autor_fkey FOREIGN KEY (id_autor) REFERENCES public.autor(id) ON UPDATE CASCADE;
 U   ALTER TABLE ONLY public.livros_autores DROP CONSTRAINT livros_autores_id_autor_fkey;
       public          postgres    false    228    4682    215            `           2606    25627 +   livros_autores livros_autores_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_autores
    ADD CONSTRAINT livros_autores_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id);
 U   ALTER TABLE ONLY public.livros_autores DROP CONSTRAINT livros_autores_id_livro_fkey;
       public          postgres    false    4694    226    228            a           2606    25632 ,   livros_generos livros_generos_id_genero_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_generos
    ADD CONSTRAINT livros_generos_id_genero_fkey FOREIGN KEY (id_genero) REFERENCES public.genero(id) ON UPDATE CASCADE;
 V   ALTER TABLE ONLY public.livros_generos DROP CONSTRAINT livros_generos_id_genero_fkey;
       public          postgres    false    224    229    4692            b           2606    25637 +   livros_generos livros_generos_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_generos
    ADD CONSTRAINT livros_generos_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id);
 U   ALTER TABLE ONLY public.livros_generos DROP CONSTRAINT livros_generos_id_livro_fkey;
       public          postgres    false    4694    229    226            �      x������ � �      �   J  x�-�=N#A��W�� WͯC@�bW G���ޥQ{��!�6�FD>B_lk�����{����*i��`�н���r����PRes�Ӏ�j<hx�CL���ML���h���#x���a��|N����m��ooKG��C���)X3�'�;��Ăk=�bqk���%�\��*&���D!�f1��%q�u�=7n��+��\���֚�Ž���v�wxt�����x��������'cё,�S�xI��X�X\�'9ȂD���Ob�,e�b�� I��`����S~��$�W��N��*���'��LΤ���?O����F�Q���_D�u倮      �      x������ � �      �      x������ � �      �     x�]P9n�0�w_�&u٥�$8��i�a�H�����H�.?�ǲR���=fg%��ר����(`�@c|`��/gY��HwC��I��Z/a��7����*g�#������C�c�-�w!0���xv�Q*8v��d���D�+��\	e�a��9��[�s�;��qA����Z����s��)ϱ�v��O�u�O^�P.�v��rN���1�ր$N4��;Q��p8Q��;T4����{s�p-7P�l´}�Uw���'D���v�      �   H   x�-û�0�:���Ǽ�GH���e^�/�U�R�� ����y㼊5� ��wft^�Փ���>5J�      �      x������ � �      �      x������ � �      �      x������ � �             x������ � �     