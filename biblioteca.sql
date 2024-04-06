PGDMP      (                 |         
   biblioteca    16.2    16.2 @               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16401 
   biblioteca    DATABASE     �   CREATE DATABASE biblioteca WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE biblioteca;
                postgres    false                       0    0    DATABASE biblioteca    COMMENT     ?   COMMENT ON DATABASE biblioteca IS 'Gerenciador de biblioteca';
                   postgres    false    4868            �            1259    16871    autor    TABLE     W   CREATE TABLE public.autor (
    id integer NOT NULL,
    nome character varying(40)
);
    DROP TABLE public.autor;
       public         heap    postgres    false            �            1259    16870    autor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.autor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.autor_id_seq;
       public          postgres    false    218                       0    0    autor_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.autor_id_seq OWNED BY public.autor.id;
          public          postgres    false    217            �            1259    16928    cidade    TABLE     q   CREATE TABLE public.cidade (
    id integer NOT NULL,
    descricao character varying(30),
    estado integer
);
    DROP TABLE public.cidade;
       public         heap    postgres    false            �            1259    16927    cidade_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cidade_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.cidade_id_seq;
       public          postgres    false    227                       0    0    cidade_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.cidade_id_seq OWNED BY public.cidade.id;
          public          postgres    false    226            �            1259    16939    cliente    TABLE     �   CREATE TABLE public.cliente (
    cpf character varying(14) NOT NULL,
    nome character varying(50),
    num_livros_emprestados integer,
    caloteiro boolean,
    id_funcionario character varying(14),
    cidade integer,
    estado integer
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    16959 
   emprestimo    TABLE     �   CREATE TABLE public.emprestimo (
    id_livro integer,
    id_cliente character varying(20),
    id_funcionario character varying(20),
    data_emprestimo date,
    data_devolucao date
);
    DROP TABLE public.emprestimo;
       public         heap    postgres    false            �            1259    16921    estado    TABLE     ]   CREATE TABLE public.estado (
    id integer NOT NULL,
    descricao character varying(30)
);
    DROP TABLE public.estado;
       public         heap    postgres    false            �            1259    16920    estado_id_seq    SEQUENCE     �   CREATE SEQUENCE public.estado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.estado_id_seq;
       public          postgres    false    225                       0    0    estado_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.estado_id_seq OWNED BY public.estado.id;
          public          postgres    false    224            �            1259    16877    funcionario    TABLE     �   CREATE TABLE public.funcionario (
    cpf character varying(14) NOT NULL,
    email character varying(50),
    nome character varying(50),
    senha character varying(20)
);
    DROP TABLE public.funcionario;
       public         heap    postgres    false            �            1259    16864    genero    TABLE     ]   CREATE TABLE public.genero (
    id integer NOT NULL,
    descricao character varying(20)
);
    DROP TABLE public.genero;
       public         heap    postgres    false            �            1259    16863    genero_id_seq    SEQUENCE     �   CREATE SEQUENCE public.genero_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.genero_id_seq;
       public          postgres    false    216            	           0    0    genero_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.genero_id_seq OWNED BY public.genero.id;
          public          postgres    false    215            �            1259    16883    livro    TABLE     �   CREATE TABLE public.livro (
    id integer NOT NULL,
    descricao character varying(50),
    qtd_estoque integer,
    id_funcionario character varying(14)
);
    DROP TABLE public.livro;
       public         heap    postgres    false            �            1259    16882    livro_id_seq    SEQUENCE     �   CREATE SEQUENCE public.livro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.livro_id_seq;
       public          postgres    false    221            
           0    0    livro_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.livro_id_seq OWNED BY public.livro.id;
          public          postgres    false    220            �            1259    16907    livros_autores    TABLE     S   CREATE TABLE public.livros_autores (
    id_livro integer,
    id_autor integer
);
 "   DROP TABLE public.livros_autores;
       public         heap    postgres    false            �            1259    16894    livros_generos    TABLE     T   CREATE TABLE public.livros_generos (
    id_livro integer,
    id_genero integer
);
 "   DROP TABLE public.livros_generos;
       public         heap    postgres    false            C           2604    16874    autor id    DEFAULT     d   ALTER TABLE ONLY public.autor ALTER COLUMN id SET DEFAULT nextval('public.autor_id_seq'::regclass);
 7   ALTER TABLE public.autor ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            F           2604    16931 	   cidade id    DEFAULT     f   ALTER TABLE ONLY public.cidade ALTER COLUMN id SET DEFAULT nextval('public.cidade_id_seq'::regclass);
 8   ALTER TABLE public.cidade ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    227    227            E           2604    16924 	   estado id    DEFAULT     f   ALTER TABLE ONLY public.estado ALTER COLUMN id SET DEFAULT nextval('public.estado_id_seq'::regclass);
 8   ALTER TABLE public.estado ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    225    225            B           2604    16867 	   genero id    DEFAULT     f   ALTER TABLE ONLY public.genero ALTER COLUMN id SET DEFAULT nextval('public.genero_id_seq'::regclass);
 8   ALTER TABLE public.genero ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            D           2604    16886    livro id    DEFAULT     d   ALTER TABLE ONLY public.livro ALTER COLUMN id SET DEFAULT nextval('public.livro_id_seq'::regclass);
 7   ALTER TABLE public.livro ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    221    221            �          0    16871    autor 
   TABLE DATA           )   COPY public.autor (id, nome) FROM stdin;
    public          postgres    false    218   �G       �          0    16928    cidade 
   TABLE DATA           7   COPY public.cidade (id, descricao, estado) FROM stdin;
    public          postgres    false    227   �G       �          0    16939    cliente 
   TABLE DATA           o   COPY public.cliente (cpf, nome, num_livros_emprestados, caloteiro, id_funcionario, cidade, estado) FROM stdin;
    public          postgres    false    228   H       �          0    16959 
   emprestimo 
   TABLE DATA           k   COPY public.emprestimo (id_livro, id_cliente, id_funcionario, data_emprestimo, data_devolucao) FROM stdin;
    public          postgres    false    229   $H       �          0    16921    estado 
   TABLE DATA           /   COPY public.estado (id, descricao) FROM stdin;
    public          postgres    false    225   AH       �          0    16877    funcionario 
   TABLE DATA           >   COPY public.funcionario (cpf, email, nome, senha) FROM stdin;
    public          postgres    false    219   ^H       �          0    16864    genero 
   TABLE DATA           /   COPY public.genero (id, descricao) FROM stdin;
    public          postgres    false    216   {H       �          0    16883    livro 
   TABLE DATA           K   COPY public.livro (id, descricao, qtd_estoque, id_funcionario) FROM stdin;
    public          postgres    false    221   �H       �          0    16907    livros_autores 
   TABLE DATA           <   COPY public.livros_autores (id_livro, id_autor) FROM stdin;
    public          postgres    false    223   �H       �          0    16894    livros_generos 
   TABLE DATA           =   COPY public.livros_generos (id_livro, id_genero) FROM stdin;
    public          postgres    false    222   �H                  0    0    autor_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.autor_id_seq', 1, false);
          public          postgres    false    217                       0    0    cidade_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cidade_id_seq', 1, false);
          public          postgres    false    226                       0    0    estado_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.estado_id_seq', 1, false);
          public          postgres    false    224                       0    0    genero_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.genero_id_seq', 1, false);
          public          postgres    false    215                       0    0    livro_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.livro_id_seq', 1, false);
          public          postgres    false    220            J           2606    16876    autor autor_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.autor DROP CONSTRAINT autor_pkey;
       public            postgres    false    218            R           2606    16933    cidade cidade_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.cidade DROP CONSTRAINT cidade_pkey;
       public            postgres    false    227            T           2606    16943    cliente cliente_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cpf);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    228            P           2606    16926    estado estado_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.estado DROP CONSTRAINT estado_pkey;
       public            postgres    false    225            L           2606    16881    funcionario funcionario_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (cpf);
 F   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_pkey;
       public            postgres    false    219            H           2606    16869    genero genero_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.genero DROP CONSTRAINT genero_pkey;
       public            postgres    false    216            N           2606    16888    livro livro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.livro DROP CONSTRAINT livro_pkey;
       public            postgres    false    221            Z           2606    16934    cidade cidade_estado_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_estado_fkey FOREIGN KEY (estado) REFERENCES public.estado(id) ON UPDATE CASCADE ON DELETE CASCADE;
 C   ALTER TABLE ONLY public.cidade DROP CONSTRAINT cidade_estado_fkey;
       public          postgres    false    227    225    4688            [           2606    16949    cliente cliente_cidade_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cidade_fkey FOREIGN KEY (cidade) REFERENCES public.cidade(id) ON UPDATE CASCADE;
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_cidade_fkey;
       public          postgres    false    4690    227    228            \           2606    16954    cliente cliente_estado_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_estado_fkey FOREIGN KEY (estado) REFERENCES public.estado(id) ON UPDATE CASCADE;
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_estado_fkey;
       public          postgres    false    4688    228    225            ]           2606    16944 #   cliente cliente_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_id_funcionario_fkey;
       public          postgres    false    4684    228    219            ^           2606    16967 %   emprestimo emprestimo_id_cliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(cpf) ON UPDATE CASCADE;
 O   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_cliente_fkey;
       public          postgres    false    4692    229    228            _           2606    16972 )   emprestimo emprestimo_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 S   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_funcionario_fkey;
       public          postgres    false    4684    229    219            `           2606    16962 #   emprestimo emprestimo_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.emprestimo DROP CONSTRAINT emprestimo_id_livro_fkey;
       public          postgres    false    4686    229    221            U           2606    16889    livro livro_id_funcionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(cpf) ON UPDATE CASCADE;
 I   ALTER TABLE ONLY public.livro DROP CONSTRAINT livro_id_funcionario_fkey;
       public          postgres    false    221    219    4684            X           2606    16915 +   livros_autores livros_autores_id_autor_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_autores
    ADD CONSTRAINT livros_autores_id_autor_fkey FOREIGN KEY (id_autor) REFERENCES public.autor(id) ON UPDATE CASCADE;
 U   ALTER TABLE ONLY public.livros_autores DROP CONSTRAINT livros_autores_id_autor_fkey;
       public          postgres    false    223    218    4682            Y           2606    16910 +   livros_autores livros_autores_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_autores
    ADD CONSTRAINT livros_autores_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id);
 U   ALTER TABLE ONLY public.livros_autores DROP CONSTRAINT livros_autores_id_livro_fkey;
       public          postgres    false    4686    223    221            V           2606    16902 ,   livros_generos livros_generos_id_genero_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_generos
    ADD CONSTRAINT livros_generos_id_genero_fkey FOREIGN KEY (id_genero) REFERENCES public.genero(id) ON UPDATE CASCADE;
 V   ALTER TABLE ONLY public.livros_generos DROP CONSTRAINT livros_generos_id_genero_fkey;
       public          postgres    false    222    4680    216            W           2606    16897 +   livros_generos livros_generos_id_livro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros_generos
    ADD CONSTRAINT livros_generos_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id);
 U   ALTER TABLE ONLY public.livros_generos DROP CONSTRAINT livros_generos_id_livro_fkey;
       public          postgres    false    4686    222    221            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     