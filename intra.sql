--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

-- Started on 2017-08-01 07:35:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 189 (class 1259 OID 16431)
-- Name: attendance_dates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE attendance_dates (
    user_id integer NOT NULL,
    event_date_id integer NOT NULL,
    attendance character varying(10),
    comment text
);


ALTER TABLE attendance_dates OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16446)
-- Name: attendances; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE attendances (
    user_id integer NOT NULL,
    event_id integer NOT NULL,
    comment text,
    updated_at timestamp with time zone
);


ALTER TABLE attendances OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16419)
-- Name: event_dates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE event_dates (
    id integer NOT NULL,
    event_id integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE event_dates OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16414)
-- Name: events; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE events (
    id integer NOT NULL,
    name character varying(40),
    answer_limit timestamp with time zone,
    commit_event_date_id smallint
);


ALTER TABLE events OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16394)
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE groups (
    id integer NOT NULL,
    parent_id integer,
    name character varying(40)
);


ALTER TABLE groups OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16404)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id integer NOT NULL,
    group_id integer,
    last_name character varying(40) NOT NULL,
    mail_address character varying(256) NOT NULL,
    password character varying(256) NOT NULL,
    family_name character varying(40)
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 2165 (class 0 OID 16431)
-- Dependencies: 189
-- Data for Name: attendance_dates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY attendance_dates (user_id, event_date_id, attendance, comment) FROM stdin;
1	1	○	\N
\.


--
-- TOC entry 2166 (class 0 OID 16446)
-- Dependencies: 190
-- Data for Name: attendances; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY attendances (user_id, event_id, comment, updated_at) FROM stdin;
1	1	参加予定だよ	\N
\.


--
-- TOC entry 2164 (class 0 OID 16419)
-- Dependencies: 188
-- Data for Name: event_dates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY event_dates (id, event_id, date) FROM stdin;
1	1	2017-08-10
2	1	2017-08-11
3	1	2017-08-12
\.


--
-- TOC entry 2163 (class 0 OID 16414)
-- Dependencies: 187
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY events (id, name, answer_limit, commit_event_date_id) FROM stdin;
1	8月帰社日	\N	\N
\.


--
-- TOC entry 2161 (class 0 OID 16394)
-- Dependencies: 185
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY groups (id, parent_id, name) FROM stdin;
1	\N	カスタマビジネス事業部
2	1	システム1課
3	1	システム2課
4	1	システム3課
\.


--
-- TOC entry 2162 (class 0 OID 16404)
-- Dependencies: 186
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, group_id, last_name, mail_address, password, family_name) FROM stdin;
111	3	昇	honda@kkhts.com	$2a$10$7XOTKcTo6y6Ot0G1V3aNMO2tZa40dY07h2GEd7kCvCBVk5nUbJFlS	本田
1	3	拓也	hashiguchi@kkhts.com	110	橋口
\.


--
-- TOC entry 2027 (class 2606 OID 16418)
-- Name: events event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY events
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- TOC entry 2033 (class 2606 OID 16435)
-- Name: attendance_dates eventattendance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY attendance_dates
    ADD CONSTRAINT eventattendance_pkey PRIMARY KEY (user_id, event_date_id);


--
-- TOC entry 2035 (class 2606 OID 16450)
-- Name: attendances eventcomment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY attendances
    ADD CONSTRAINT eventcomment_pkey PRIMARY KEY (user_id, event_id);


--
-- TOC entry 2029 (class 2606 OID 16425)
-- Name: event_dates eventdate_id_date_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY event_dates
    ADD CONSTRAINT eventdate_id_date_key UNIQUE (id, date);


--
-- TOC entry 2031 (class 2606 OID 16423)
-- Name: event_dates eventdate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY event_dates
    ADD CONSTRAINT eventdate_pkey PRIMARY KEY (id);


--
-- TOC entry 2023 (class 2606 OID 16398)
-- Name: groups group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);


--
-- TOC entry 2025 (class 2606 OID 16408)
-- Name: users user_tran_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_tran_pkey PRIMARY KEY (id);


--
-- TOC entry 2041 (class 2606 OID 16441)
-- Name: attendance_dates eventattendance_eventdateid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY attendance_dates
    ADD CONSTRAINT eventattendance_eventdateid_fkey FOREIGN KEY (event_date_id) REFERENCES event_dates(id);


--
-- TOC entry 2040 (class 2606 OID 16436)
-- Name: attendance_dates eventattendance_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY attendance_dates
    ADD CONSTRAINT eventattendance_userid_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2043 (class 2606 OID 16456)
-- Name: attendances eventcomment_eventid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY attendances
    ADD CONSTRAINT eventcomment_eventid_fkey FOREIGN KEY (event_id) REFERENCES events(id);


--
-- TOC entry 2042 (class 2606 OID 16451)
-- Name: attendances eventcomment_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY attendances
    ADD CONSTRAINT eventcomment_userid_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2039 (class 2606 OID 16426)
-- Name: event_dates eventdate_eventid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY event_dates
    ADD CONSTRAINT eventdate_eventid_fkey FOREIGN KEY (event_id) REFERENCES events(id);


--
-- TOC entry 2038 (class 2606 OID 24585)
-- Name: events events_cmmit_event_date_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY events
    ADD CONSTRAINT events_cmmit_event_date_id_fkey FOREIGN KEY (commit_event_date_id) REFERENCES event_dates(id);


--
-- TOC entry 2036 (class 2606 OID 16399)
-- Name: groups group_parentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT group_parentid_fkey FOREIGN KEY (parent_id) REFERENCES groups(id);


--
-- TOC entry 2037 (class 2606 OID 16409)
-- Name: users user_tran_groupid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_tran_groupid_fkey FOREIGN KEY (group_id) REFERENCES groups(id);


-- Completed on 2017-08-01 07:35:26

--
-- PostgreSQL database dump complete
--

