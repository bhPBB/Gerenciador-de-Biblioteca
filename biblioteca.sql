PGDMP  4                    |         
   biblioteca    16.2    16.2 R               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            !           1262    16986 
   biblioteca    DATABASE     �   CREATE DATABASE biblioteca WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE biblioteca;
                postgres    false            "           0    0    DATABASE biblioteca    COMMENT     s   COMMENT ON DATABASE biblioteca IS 'Banco de dados de um gerenciador de biblioteca feito para um trabalho de Java';
                   postgres    false    4897            �            1259    16987    autor    TABLE     W   CREATE TABLE public.autor (
    id integer NOT NULL,
    nome character varying(40)
);
    DROP TABLE public.autor;
       public         heap    postgres    false            �            1259    16990    autor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.autor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.autor_id_seq;
       public          postgres    false    215            #           0    0    autor_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.autor_id_seq OWNED BY public.autor.id;
          public          postgres    false    216            �            1259    16991    cidade    TABLE     q   CREATE TABLE public.cidade (
    id integer NOT NULL,
    descricao character varying(30),
    estado integer
);
    DROP TABLE public.cidade;
       public         heap    postgres    false            �            1259    16994    cidade_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cidade_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.cidade_id_seq;
       public          postgres    false    217            $           0    0    cidade_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.cidade_id_seq OWNED BY public.cidade.id;
          public          postgres    false    218            �            1259    16995    cliente    TABLE       CREATE TABLE public.cliente (
    cpf character varying(14) NOT NULL,
    nome character varying(50),
    num_livros_emprestados integer DEFAULT 0,
    caloteiro boolean DEFAULT false,
    id_funcionario character varying(14),
    cidade integer,
    estado integer
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    17000 
   emprestimo    TABLE     �   CREATE TABLE public.emprestimo (
    id_livro integer,
    id_cliente character varying(20),
    id_funcionario character varying(20),
    data_emprestimo date,
    data_devolucao date
);
    DROP TABLE public.emprestimo;
       public         heap    postgres    false            �            1259    17003    estado    TABLE     ]   CREATE TABLE public.estado (
    id integer NOT NULL,
    descricao character varying(30)
);
    DROP TABLE public.estado;
       public         heap    postgres    false            �            1259    17006    estado_id_seq    SEQUENCE     �   CREATE SEQUENCE public.estado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.estado_id_seq;
       public          postgres    false    221            %           0    0    estado_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.estado_id_seq OWNED BY public.estado.id;
          public          postgres    false    222            �            1259    17007    funcionario    TABLE     �   CREATE TABLE public.funcionario (
    cpf character varying(14) NOT NULL,
    email character varying(50),
    nome character varying(50),
    senha text
);
    DROP TABLE public.funcionario;
       public         heap    postgres    false            �            1259    17012    genero    TABLE     ]   CREATE TABLE public.genero (
    id integer NOT NULL,
    descricao character varying(20)
);
    DROP TABLE public.genero;
       public         heap    postgres    false            �            1259    17015    genero_id_seq    SEQUENCE     �   CREATE SEQUENCE public.genero_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.genero_id_seq;
       public          postgres    false    224            &           0    0    genero_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.genero_id_seq OWNED BY public.genero.id;
          public          postgres    false    225            �            1259    17016    livro    TABLE     �   CREATE TABLE public.livro (
    id integer NOT NULL,
    descricao character varying(50),
    qtd_estoque integer,
    id_funcionario character varying(14),
    imagem bytea
);
    DROP TABLE public.livro;
       public         heap    postgres    false            �            1259    17019    livro_id_seq    SEQUENCE     �   CREATE SEQUENCE public.livro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.livro_id_seq;
       public          postgres    false    226            '           0    0    livro_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.livro_id_seq OWNED BY public.livro.id;
          public          postgres    false    227            �            1259    17020    livros_autores    TABLE     S   CREATE TABLE public.livros_autores (
    id_livro integer,
    id_autor integer
);
 "   DROP TABLE public.livros_autores;
       public         heap    postgres    false            �            1259    17023    livros_generos    TABLE     T   CREATE TABLE public.livros_generos (
    id_livro integer,
    id_genero integer
);
 "   DROP TABLE public.livros_generos;
       public         heap    postgres    false            �            1259    17112    origem    TABLE     8   CREATE TABLE public.origem (
    id integer NOT NULL
);
    DROP TABLE public.origem;
       public         heap    postgres    false            �            1259    17128    origem2    TABLE     9   CREATE TABLE public.origem2 (
    id integer NOT NULL
);
    DROP TABLE public.origem2;
       public         heap    postgres    false            �            1259    17127    origem2_id_seq    SEQUENCE     �   CREATE SEQUENCE public.origem2_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.origem2_id_seq;
       public          postgres    false    233            (           0    0    origem2_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.origem2_id_seq OWNED BY public.origem2.id;
          public          postgres    false    232            �            1259    17111    origem_id_seq    SEQUENCE     �   CREATE SEQUENCE public.origem_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.origem_id_seq;
       public          postgres    false    231            )           0    0    origem_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.origem_id_seq OWNED BY public.origem.id;
          public          postgres    false    230            �            1259    17134    testada    TABLE     E   CREATE TABLE public.testada (
    fore integer,
    fore2 integer
);
    DROP TABLE public.testada;
       public         heap    postgres    false            P           2604    17026    autor id    DEFAULT     d   ALTER TABLE ONLY public.autor ALTER COLUMN id SET DEFAULT nextval('public.autor_id_seq'::regclass);
 7   ALTER TABLE public.autor ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            Q           2604    17027 	   cidade id    DEFAULT     f   ALTER TABLE ONLY public.cidade ALTER COLUMN id SET DEFAULT nextval('public.cidade_id_seq'::regclass);
 8   ALTER TABLE public.cidade ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            T           2604    17028 	   estado id    DEFAULT     f   ALTER TABLE ONLY public.estado ALTER COLUMN id SET DEFAULT nextval('public.estado_id_seq'::regclass);
 8   ALTER TABLE public.estado ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221            U           2604    17029 	   genero id    DEFAULT     f   ALTER TABLE ONLY public.genero ALTER COLUMN id SET DEFAULT nextval('public.genero_id_seq'::regclass);
 8   ALTER TABLE public.genero ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224            V           2604    17030    livro id    DEFAULT     d   ALTER TABLE ONLY public.livro ALTER COLUMN id SET DEFAULT nextval('public.livro_id_seq'::regclass);
 7   ALTER TABLE public.livro ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    226            W           2604    17115 	   origem id    DEFAULT     f   ALTER TABLE ONLY public.origem ALTER COLUMN id SET DEFAULT nextval('public.origem_id_seq'::regclass);
 8   ALTER TABLE public.origem ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    230    231            X           2604    17131 
   origem2 id    DEFAULT     h   ALTER TABLE ONLY public.origem2 ALTER COLUMN id SET DEFAULT nextval('public.origem2_id_seq'::regclass);
 9   ALTER TABLE public.origem2 ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    232    233                      0    16987    autor 
   TABLE DATA           )   COPY public.autor (id, nome) FROM stdin;
    public          postgres    false    215   Z       
          0    16991    cidade 
   TABLE DATA           7   COPY public.cidade (id, descricao, estado) FROM stdin;
    public          postgres    false    217   %Z                 0    16995    cliente 
   TABLE DATA           o   COPY public.cliente (cpf, nome, num_livros_emprestados, caloteiro, id_funcionario, cidade, estado) FROM stdin;
    public          postgres    false    219   [                 0    17000 
   emprestimo 
   TABLE DATA           k   COPY public.emprestimo (id_livro, id_cliente, id_funcionario, data_emprestimo, data_devolucao) FROM stdin;
    public          postgres    false    220   �[                 0    17003    estado 
   TABLE DATA           /   COPY public.estado (id, descricao) FROM stdin;
    public          postgres    false    221   �[                 0    17007    funcionario 
   TABLE DATA           >   COPY public.funcionario (cpf, email, nome, senha) FROM stdin;
    public          postgres    false    223   �\                 0    17012    genero 
   TABLE DATA           /   COPY public.genero (id, descricao) FROM stdin;
    public          postgres    false    224   ,]                 0    17016    livro 
   TABLE DATA           S   COPY public.livro (id, descricao, qtd_estoque, id_funcionario, imagem) FROM stdin;
    public          postgres    false    226   I]                 0    17020    livros_autores 
   TABLE DATA           <   COPY public.livros_autores (id_livro, id_autor) FROM stdin;
    public          postgres    false    228   f]                 0    17023    livros_generos 
   TABLE DATA           =   COPY public.livros_generos (id_livro, id_genero) FROM stdin;
    public          postgres    false    229   �]                 0    17112    origem 
   TABLE DATA           $   COPY public.origem (id) FROM stdin;
    public          postgres    false    231   �]                 0    17128    origem2 
   TABLE DATA           %   COPY public.origem2 (id) FROM stdin;
    public          postgres    false    233   �]                 0    17134    testada 
   TABLE DATA           .   COPY public.testada (fore, fore2) FROM stdin;
    public          postgres    false    234   &^       *           0    0    autor_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.autor_id_seq', 1, false);
          public          postgres    false    216            +           0    0    cidade_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cidade_id_seq', 28, true);
          public          postgres    false    218            ,           0    0    estado_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.estado_id_seq', 27, true);
          public          postgres    false    222            -           0    0    genero_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.genero_id_seq', 1, false);
          public          postgres    false    225            .           0    0    livro_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.livro_id_seq', 1, false);
          public          postgres    false    227            /           0    0    origem2_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.origem2_id_seq', 15, true);
          public          postgres    false    232            0           0    0    origem_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.origem_id_seq', 32, true);
          public          postgres    false    230            Z           2606    17032    autor autor_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.autor DROP CONSTRAINT autor_pkey;
       public            postgres    false    215            \           2606    17034    cidade cidade_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.cidade DROP CONSTRAINT cidade_pkey;
       public            postgres    false    217            ^           2606    17036    cliente cliente_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cpf);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    219            `           2606    17038    estado estado_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.estado DROP CONSTRAINT estado_pkey;
       public            postgres    false    221            b           2606    17040    funcionario funcionario_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (cpf);
 F   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_pkey;
       public            postgres    false    223            d           2606    17042    genero genero_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.genero DROP CONSTRAINT genero_pkey;
       public            postgres    false    224            f           2606    17044    livro livro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.livro DROP CONSTRAINT livro_pkey;
       public            postgres    false    226            j           2606    17133    origem2 origem2_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.origem2
    ADD CONSTRAINT origem2_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.origem2 DROP CONSTRAINT origem2_pkey;
       public            postgres    false    233            h           2606    17117    origem origem_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.origem
    ADD CONSTRAINT origem_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.origem DROP CONSTRAINT origem_pkey;
       public            postgres    false    231            k           2606    17045    cidade cidade_estado_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_estado_fkey FOREIGN KEY (estado) REFERENCES public.estado(id) ON UPDATE CASCADE ON DELETE CASCADE;
 C   ALTER TABLE ONLY public.cidade DROP CONSTRAINT cidade_estado_fkey;
       public          postgres    false    221    217    4704            l           2606    17050    cliente cliente_cidade_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cidade_fkey FOREIGN KEY (cidade) REFERENCES public.cidade(id) ON UPDATE CASCADE;
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_cidade_fkey;
       public          postgres    false    217    4700    219            m           2606    17055    cliente cliente_estado_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_estado_fkey FOREIGN KEY (estado) REFERENCES public.estado(id) ON UPDATE CASCADE;
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_estado_fkey;
       public          postgres    false    219    4704    221            n           2606    17060 #   cliente cliente_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_id_funcionario_fkey;
       public          postgres    false    223    4706    219            o           2606    17065 %   emprestimo emprestimo_id_cliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(cpf) ON UPDATE CASCADE;
 O   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_cliente_fkey;
       public          postgres    false    220    4702    219            p           2606    17070 )   emprestimo emprestimo_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 S   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_funcionario_fkey;
       public          postgres    false    4706    220    223            q           2606    17075 #   emprestimo emprestimo_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_livro_fkey;
       public          postgres    false    220    226    4710            r           2606    17080    livro livro_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 I   ALTER TABLE ONLY public.livro DROP CONSTRAINT livro_id_funcionario_fkey;
       public          postgres    false    223    4706    226            s           2606    17085 +   livros_autores livros_autores_id_autor_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_autores
    ADD CONSTRAINT livros_autores_id_autor_fkey FOREIGN KEY (id_autor) REFERENCES public.autor(id) ON UPDATE CASCADE;
 U   ALTER TABLE ONLY public.livros_autores DROP CONSTRAINT livros_autores_id_autor_fkey;
       public          postgres    false    4698    215    228            t           2606    17090 +   livros_autores livros_autores_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_autores
    ADD CONSTRAINT livros_autores_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id);
 U   ALTER TABLE ONLY public.livros_autores DROP CONSTRAINT livros_autores_id_livro_fkey;
       public          postgres    false    226    228    4710            u           2606    17095 ,   livros_generos livros_generos_id_genero_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_generos
    ADD CONSTRAINT livros_generos_id_genero_fkey FOREIGN KEY (id_genero) REFERENCES public.genero(id) ON UPDATE CASCADE;
 V   ALTER TABLE ONLY public.livros_generos DROP CONSTRAINT livros_generos_id_genero_fkey;
       public          postgres    false    4708    224    229            v           2606    17100 +   livros_generos livros_generos_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_generos
    ADD CONSTRAINT livros_generos_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id);
 U   ALTER TABLE ONLY public.livros_generos DROP CONSTRAINT livros_generos_id_livro_fkey;
       public          postgres    false    226    4710    229            w           2606    17142    testada testada_fore2_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.testada
    ADD CONSTRAINT testada_fore2_fkey FOREIGN KEY (fore2) REFERENCES public.origem2(id);
 D   ALTER TABLE ONLY public.testada DROP CONSTRAINT testada_fore2_fkey;
       public          postgres    false    4714    233    234            x           2606    17137    testada testada_fore_fkey    FK CONSTRAINT     v   ALTER TABLE ONLY public.testada
    ADD CONSTRAINT testada_fore_fkey FOREIGN KEY (fore) REFERENCES public.origem(id);
 C   ALTER TABLE ONLY public.testada DROP CONSTRAINT testada_fore_fkey;
       public          postgres    false    234    4712    231                  x������ � �      
   J  x�-�=N#A��W�� WͯC@�bW G���ޥQ{��!�6�FD>B_lk�����{����*i��`�н���r����PRes�Ӏ�j<hx�CL���ML���h���#x���a��|N����m��ooKG��C���)X3�'�;��Ăk=�bqk���%�\��*&���D!�f1��%q�u�=7n��+��\���֚�Ž���v�wxt�����x��������'cё,�S�xI��X�X\�'9ȂD���Ob�,e�b�� I��`����S~��$�W��N��*���'��LΤ���?O����F�Q���_D�u倮            x������ � �            x������ � �           x�]P9n�0�w_�&u٥�$8��i�a�H�����H�.?�ǲR���=fg%��ר����(`�@c|`��/gY��HwC��I��Z/a��7����*g�#������C�c�-�w!0���xv�Q*8v��d���D�+��\	e�a��9��[�s�;��qA����Z����s��)ϱ�v��O�u�O^�P.�v��rN���1�ր$N4��;Q��p8Q��;T4����{s�p-7P�l´}�Uw���'D���v�         H   x�-û�0�:���Ǽ�GH���e^�/�U�R�� ����y㼊5� ��wft^�Փ���>5J�            x������ � �            x������ � �            x������ � �            x������ � �         >   x����0�?��\�G/鿎 Qc;n��'K"��-Gny�O�F��[���諟Y&�~�O��         (   x��I   �?a��.��!D�ӲW�D�t2��qq            x������ � �     