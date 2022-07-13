--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-07-10 21:51:45

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 111849)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id bigint NOT NULL,
    account_number character varying(255),
    amount numeric(19,2),
    date_opened timestamp without time zone,
    is_closed boolean,
    client_id bigint
);


ALTER TABLE public.account OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 30163)
-- Name: account_gen; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_gen
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_gen OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 111842)
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_id_seq OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 111854)
-- Name: account_plan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_plan (
    id bigint NOT NULL,
    description character varying(255),
    min_amount numeric(19,2),
    min_period bigint,
    percent numeric(19,2),
    title character varying(255)
);


ALTER TABLE public.account_plan OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 111843)
-- Name: account_plan_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_plan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_plan_id_seq OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 111861)
-- Name: card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.card (
    id bigint NOT NULL,
    card_number character varying(255),
    is_blocked boolean,
    is_closed boolean,
    pin character varying(255),
    account_id bigint,
    client_id bigint
);


ALTER TABLE public.card OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 111844)
-- Name: card_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.card_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.card_id_seq OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 105578)
-- Name: card_plan_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.card_plan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.card_plan_id_seq OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 111868)
-- Name: checking_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.checking_account (
    is_default boolean,
    id bigint NOT NULL
);


ALTER TABLE public.checking_account OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 47054)
-- Name: checking_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.checking_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.checking_account_id_seq OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 111873)
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id bigint NOT NULL,
    email character varying(255),
    phone_number character varying(255),
    user_id bigint
);


ALTER TABLE public.client OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 30164)
-- Name: client_gen; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_gen
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_gen OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 111845)
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 30112)
-- Name: loan_account_gen; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.loan_account_gen
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_account_gen OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 30165)
-- Name: loan_gen; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.loan_gen
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_gen OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 105580)
-- Name: loan_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.loan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_id_seq OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 30166)
-- Name: person_gen; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_gen
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_gen OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 39989)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 111880)
-- Name: saving_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.saving_account (
    period bigint,
    id bigint NOT NULL,
    plan_id bigint
);


ALTER TABLE public.saving_account OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 47057)
-- Name: saving_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.saving_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.saving_account_id_seq OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 111885)
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    id bigint NOT NULL,
    amount numeric(19,2),
    date timestamp without time zone,
    status character varying(255),
    source_account_id bigint,
    target_account_id bigint
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 111846)
-- Name: transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_id_seq OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 111890)
-- Name: transaction_statistics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction_statistics (
    id bigint NOT NULL,
    date timestamp without time zone,
    type character varying(255)
);


ALTER TABLE public.transaction_statistics OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 111847)
-- Name: transaction_statistics_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_statistics_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_statistics_id_seq OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 111848)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 111895)
-- Name: usr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usr (
    id bigint NOT NULL,
    date_register timestamp without time zone,
    login character varying(255) NOT NULL,
    name character varying(255),
    password character varying(255) NOT NULL,
    role character varying(255),
    status character varying(255),
    surname character varying(255)
);


ALTER TABLE public.usr OWNER TO postgres;

--
-- TOC entry 3399 (class 0 OID 111849)
-- Dependencies: 226
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (id, account_number, amount, date_opened, is_closed, client_id) FROM stdin;
1	4213924909090	758432.00	2022-07-10 20:53:36.373926	f	3
4	4263736909090	100000.00	2022-05-10 20:58:29.186	f	3
5	4288760909090	7000.00	2022-05-10 20:58:56.382	f	3
6	4213515909090	150000.00	2022-05-10 20:59:07.121	f	3
2	4220602909090	82009.00	2022-07-10 20:53:39.994453	f	3
3	4207665909090	13669.00	2022-07-10 20:56:39.22587	f	3
\.


--
-- TOC entry 3400 (class 0 OID 111854)
-- Dependencies: 227
-- Data for Name: account_plan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account_plan (id, description, min_amount, min_period, percent, title) FROM stdin;
1	Ставка 3% годовых. Любая сумма вклада. Срок от 1 месяца.	0.00	1	3.00	Накопительный счет 3%
2	Высокая ставка 11%. Сумма от 100 000, срок от 12 месяцев.	100000.00	12	11.00	Вклад доходный 11%
3	Сохраняйте ваши сбережения со ставкой 7%. Сумма от 10000, срок от 3 месяцев.	10000.00	3	7.00	Вклад сохраняй 7%
\.


--
-- TOC entry 3401 (class 0 OID 111861)
-- Dependencies: 228
-- Data for Name: card; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.card (id, card_number, is_blocked, is_closed, pin, account_id, client_id) FROM stdin;
1	6269798059138349	f	f	\N	3	3
\.


--
-- TOC entry 3402 (class 0 OID 111868)
-- Dependencies: 229
-- Data for Name: checking_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.checking_account (is_default, id) FROM stdin;
t	1
f	2
\.


--
-- TOC entry 3403 (class 0 OID 111873)
-- Dependencies: 230
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (id, email, phone_number, user_id) FROM stdin;
2	lediburit@mail.ru	89063962717	2
3	ivan@mail.ru	8900909090	3
\.


--
-- TOC entry 3404 (class 0 OID 111880)
-- Dependencies: 231
-- Data for Name: saving_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.saving_account (period, id, plan_id) FROM stdin;
12	4	2
2	5	1
24	6	3
\.


--
-- TOC entry 3405 (class 0 OID 111885)
-- Dependencies: 232
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction (id, amount, date, status, source_account_id, target_account_id) FROM stdin;
1	777777.00	2022-07-10 20:55:08.189229	DONE	1	1
2	333333.00	2022-07-10 20:56:12.206633	DONE	2	2
3	12345.00	2022-07-10 20:56:49.714307	DONE	1	3
4	100000.00	2022-07-10 20:58:29.192557	DONE	2	4
5	7000.00	2022-07-10 20:58:56.385362	DONE	1	5
6	150000.00	2022-07-10 20:59:07.123993	DONE	2	6
7	1324.00	2022-07-10 21:02:49.353522	DONE	2	3
\.


--
-- TOC entry 3406 (class 0 OID 111890)
-- Dependencies: 233
-- Data for Name: transaction_statistics; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction_statistics (id, date, type) FROM stdin;
1	2022-07-10 20:56:49.719274	CARD
2	2022-07-10 21:02:49.354523	PHONE
3	2022-07-10 21:05:25.579672	PHONE
4	2022-07-10 21:05:59.303004	PHONE
\.


--
-- TOC entry 3407 (class 0 OID 111895)
-- Dependencies: 234
-- Data for Name: usr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usr (id, date_register, login, name, password, role, status, surname) FROM stdin;
1	2022-07-10 20:32:32.156899	11111111	admin	$2a$12$yHGabWhz1FK6GF6dUQelFO76yYDQvYRSa1zIUqN1F1XXU553WEz6G	ADMIN	ACTIVE	admin
2	2022-07-10 20:49:40.576031	89063962717	Дмитрий	$2a$12$yKwVwY8m9DBOSZWcG0mpau42choKHWonXFscgp4rE2WUejs0r312W	USER	ACTIVE	Лебедев
3	2022-07-10 20:51:08.80714	8900909090	Иван	$2a$12$RTiSFBLf8oXovBCZieFWB.WxobLfdXpRXhJSHl4yTiF.gCYW1vCyq	USER	ACTIVE	Васильев
\.


--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 210
-- Name: account_gen; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_gen', 1, false);


--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 219
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_id_seq', 6, true);


--
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 220
-- Name: account_plan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_plan_id_seq', 3, true);


--
-- TOC entry 3417 (class 0 OID 0)
-- Dependencies: 221
-- Name: card_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.card_id_seq', 1, true);


--
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 217
-- Name: card_plan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.card_plan_id_seq', 1, false);


--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 215
-- Name: checking_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.checking_account_id_seq', 1, false);


--
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 211
-- Name: client_gen; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_gen', 1, false);


--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 222
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 3, true);


--
-- TOC entry 3422 (class 0 OID 0)
-- Dependencies: 209
-- Name: loan_account_gen; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.loan_account_gen', 1, false);


--
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 212
-- Name: loan_gen; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.loan_gen', 1, false);


--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 218
-- Name: loan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.loan_id_seq', 1, false);


--
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 213
-- Name: person_gen; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_gen', 1, false);


--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 214
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 1, true);


--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 216
-- Name: saving_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.saving_account_id_seq', 1, false);


--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 223
-- Name: transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_id_seq', 7, true);


--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 224
-- Name: transaction_statistics_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_statistics_id_seq', 4, true);


--
-- TOC entry 3430 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 3, true);


--
-- TOC entry 3213 (class 2606 OID 111853)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 3215 (class 2606 OID 111860)
-- Name: account_plan account_plan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_plan
    ADD CONSTRAINT account_plan_pkey PRIMARY KEY (id);


--
-- TOC entry 3217 (class 2606 OID 111867)
-- Name: card card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_pkey PRIMARY KEY (id);


--
-- TOC entry 3219 (class 2606 OID 111872)
-- Name: checking_account checking_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.checking_account
    ADD CONSTRAINT checking_account_pkey PRIMARY KEY (id);


--
-- TOC entry 3221 (class 2606 OID 111879)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- TOC entry 3225 (class 2606 OID 111884)
-- Name: saving_account saving_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saving_account
    ADD CONSTRAINT saving_account_pkey PRIMARY KEY (id);


--
-- TOC entry 3227 (class 2606 OID 111889)
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);


--
-- TOC entry 3229 (class 2606 OID 111894)
-- Name: transaction_statistics transaction_statistics_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction_statistics
    ADD CONSTRAINT transaction_statistics_pkey PRIMARY KEY (id);


--
-- TOC entry 3223 (class 2606 OID 111903)
-- Name: client uk_1ixfyfepst9sjbo9op1v65fg0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_1ixfyfepst9sjbo9op1v65fg0 UNIQUE (user_id);


--
-- TOC entry 3231 (class 2606 OID 111905)
-- Name: usr uk_b2j2bjirhqhbg1rsexaq5qs9x; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usr
    ADD CONSTRAINT uk_b2j2bjirhqhbg1rsexaq5qs9x UNIQUE (login);


--
-- TOC entry 3233 (class 2606 OID 111901)
-- Name: usr usr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usr
    ADD CONSTRAINT usr_pkey PRIMARY KEY (id);


--
-- TOC entry 3241 (class 2606 OID 111941)
-- Name: transaction fk25e716ukpqahttjt6c487lrer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fk25e716ukpqahttjt6c487lrer FOREIGN KEY (source_account_id) REFERENCES public.account(id);


--
-- TOC entry 3235 (class 2606 OID 111911)
-- Name: card fk8v67eys6tqflsm6hrdgru2phu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT fk8v67eys6tqflsm6hrdgru2phu FOREIGN KEY (account_id) REFERENCES public.account(id);


--
-- TOC entry 3237 (class 2606 OID 111921)
-- Name: checking_account fkc7fxiw236f4kvag916rq4udds; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.checking_account
    ADD CONSTRAINT fkc7fxiw236f4kvag916rq4udds FOREIGN KEY (id) REFERENCES public.account(id);


--
-- TOC entry 3238 (class 2606 OID 111926)
-- Name: client fkf4myvb2x4ifkgqa8sawl1r6op; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT fkf4myvb2x4ifkgqa8sawl1r6op FOREIGN KEY (user_id) REFERENCES public.usr(id);


--
-- TOC entry 3240 (class 2606 OID 111936)
-- Name: saving_account fkiur0263vv3myj6kwk0fa2j3fp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saving_account
    ADD CONSTRAINT fkiur0263vv3myj6kwk0fa2j3fp FOREIGN KEY (id) REFERENCES public.account(id);


--
-- TOC entry 3234 (class 2606 OID 111906)
-- Name: account fkkm8yb63h4ownvnlrbwnadntyn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkkm8yb63h4ownvnlrbwnadntyn FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- TOC entry 3242 (class 2606 OID 111946)
-- Name: transaction fkmj1os7uxmi54buhn6tvro5i6j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fkmj1os7uxmi54buhn6tvro5i6j FOREIGN KEY (target_account_id) REFERENCES public.account(id);


--
-- TOC entry 3236 (class 2606 OID 111916)
-- Name: card fknvnei204yqg1f980ne5doh3y8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT fknvnei204yqg1f980ne5doh3y8 FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- TOC entry 3239 (class 2606 OID 111931)
-- Name: saving_account fkqmlttavsax9998vkp5uo45v3n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saving_account
    ADD CONSTRAINT fkqmlttavsax9998vkp5uo45v3n FOREIGN KEY (plan_id) REFERENCES public.account_plan(id);


-- Completed on 2022-07-10 21:51:45

--
-- PostgreSQL database dump complete
--

