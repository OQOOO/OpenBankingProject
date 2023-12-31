<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        html {
            background-color: rgb(39, 39, 39);
        }

        #container {
            position: absolute;
            width: 700px;
            height: 300px;
            border: 1px solid black;
            background-image: url("game/mario-background_4x.jpg");
            background-size: cover;
            background-position-y: -220px;

            animation: slide 5s linear infinite;
        }

        #startPage {
            position: relative;
            width: 700px;
            height: 300px;
            background-color: #0000009a;
            z-index: 10;
            text-align: center;
        }

        #startPage button {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 300px;
            height: 45px;
            font-size: 20px;
            transform: translate(-50%, -50%);
        }

        #startPage button:active {
            background-color: rgb(202, 255, 237);
        }

        #manual {
            position: absolute;
            width: 300px;
            margin-top: 300px;
            padding-left: 10px;
            color: white;
            text-align: left;
        }


        #gameOverPage {
            position: relative;
            width: 700px;
            height: 300px;
            background-color: #0000009a;
            z-index: 10;
            text-align: center;
            display: none;
        }

        #gameOverPage button {
            position: absolute;
            width: 200px;
            height: 40px;


            left: 50%;
            bottom: 50px;
            transform: translate(-50%, 0%);
        }

        #gameOverText {
            margin: 0px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -90%);
            color: rgb(255, 255, 255);
            font-size: 60px;
        }

        #lastScore {
            margin: 0px;
            position: absolute;
            top: 60%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: rgb(255, 255, 255);
            font-size: 15px;
            z-index: 11;
        }

        #filter {

            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            transition: background-color 1s ease;
        }

        @keyframes slide {
            0% {
                background-position: 0 -220px;
            }

            100% {
                background-position: -700px -220px;
                /* 이미지가 이동하는 거리 */
            }
        }

        #character {
            width: 50px;
            height: 50px;
            background-color: rgba(255, 0, 0, 0);
            position: absolute;
            bottom: 48px;
            /* 원래 0인데 48로 바뀜. 이에 맞춰 여러가지 전부 바꾸는 작업 수행중*/
            left: 30px;
        }

        #character img {
            position: relative;
            top: 5px;
            width: 100%;
            height: 100%;
        }

        .enemy {
            position: absolute;
            bottom: 58px;
            /**/
            left: 700px;
            width: 30px;
            height: 30px;
            background-color: rgba(0, 0, 255, 0);
        }

        .enemy img {
            width: 100%;
            border: 1px solid rgba(0, 0, 255, 0);

        }

        .enemy2 {
            position: absolute;
            bottom: 58px;
            /**/
            left: 700px;
            width: 30px;
            height: 30px;
            background-color: rgba(0, 0, 255, 0);
        }

        .enemy2 img {
            width: 100%;
            height: 100%;
            border: 1px solid rgba(0, 0, 255, 0);
            transform: scaleX(-1);
            filter: brightness(2);
        }

        .turtle {
            position: absolute;
            bottom: 70px;
            /**/
            left: 700px;
            width: 30px;
            height: 30px;
            background-color: rgba(0, 0, 255, 0);
        }

        .turtle img {
            width: 100%;
            height: 100%;
            border: 1px solid rgba(0, 0, 255, 0);
            transform: scaleX(-1);
            filter: brightness(2);
        }

        .koopa {
            position: absolute;
            bottom: 70px;
            /**/
            left: 700px;
            width: 70px;
            height: 70px;
            background-color: rgba(0, 0, 255, 0);
        }

        .koopa img {
            width: 100%;
            height: 100%;
            border: 1px solid rgba(0, 0, 255, 0);
            transform: scaleX(-1);
            filter: brightness(2);
        }




        #point {
            margin-top: 30px;
            text-align: center;
            display: none;
        }

        .attack img {
            transform: scaleX(-1);
            width: 70%;
            height: 70%;
        }

        .shellAttack img {
            width: 100%;
            height: 100%;
        }

        #trampleEffect {
            position: absolute;
            width: 50px;
            height: 50px;
            bottom: 40px;
            left: 35px;
            background-color: rgb(255, 231, 181);
            border-radius: 50px;
            display: none;
        }

        #explosionEffect {
            position: absolute;
            width: 100px;
            height: 100px;
            bottom: 20px;
            left: 35px;
            background-color: rgb(255, 243, 177);
            border-radius: 50px;
            display: none;
        }

        #koopaHealthBarBack {
            position: absolute;
            width: 400px;
            height: 10px;
            background-color: rgb(255, 255, 255);
            top: 10px;
            left: 20%;
            border: 1px solid black;
            display: none;
        }

        #koopaHealthBar {
            position: absolute;
            width: 400px;
            height: 10px;
            background-color: rgb(255, 0, 0);
            top: 10px;
            left: 20%;
            border: 1px solid black;
            display: none;
        }

        #cheat {
            position: absolute;
            left: 602px;
            top: 310px;
            width: 100px;
            background-color: #00000000;
            color: #00000000;
            border: 0px;
        }
    </style>


    <script>
        // setInterval(function () {}, 20);
        // 스레드 생성

        let point = 0;
        const GROUND_HEIGHT = 48;
        let isGameOver = false;

        let isKoopaDeath = false;
        function gameStart() {
            isGameStart = true;
            document.getElementById('point').style.display = 'block';
            point += parseInt(document.getElementById('cheat').value);

            // 시작페이지 가리기
            const startPage = document.getElementById("startPage");
            startPage.style.display = 'none';

            const character = document.getElementById('character');
            let currentPosition = GROUND_HEIGHT; // 시작높이

            /**
             * 점프 함수
             * 
             * 점프 시작 후 중력가속도로 속도 감소 -> 하락
             * 
             * 다시 땅에 닿으면 함수 종료
             **/
            // 관련 변수
            let isJumping = false;
            let isMarioTrample = false;
            //
            function jump() {
                let gravity = 1;
                let jumpSpeed = 15;

                let jumpInterval = setInterval(function () {
                    isJumping = true;

                    jumpSpeed -= gravity;
                    if (jumpSpeed < 0 && currentPosition > GROUND_HEIGHT + 20) {
                        isMarioTrample = true;
                    } else {
                        isMarioTrample = false;
                    }

                    currentPosition += jumpSpeed;
                    character.style.bottom = currentPosition + 'px';

                    if (currentPosition <= GROUND_HEIGHT) {
                        clearInterval(jumpInterval);
                        isJumping = false;
                    }
                }, 20);
            }

            /*
            =================================================================================================================================================================================

                                    공격 관련 함수들

            =================================================================================================================================================================================
            */

            // 화면효과용
            const trampleEffect = document.getElementById("trampleEffect");
            const explosionEffect = document.getElementById("explosionEffect");
            let pointBox = document.getElementById("point");

            let isAttacking = false; // 연속실행방지
            let AttackCnt = 0; // 화면에 존재하는 공격 수. 오류방지 및 함수 종료용

            // 미사일 생성
            function characterAttack() {
                AttackCnt++;

                isFirstAttack = true;
                isAttacking = true;

                const attack = document.createElement('div');
                attack.classList.add("attack");
                attack.style.width = "30px";
                attack.style.height = "30px";
                attack.style.position = "absolute";
                attack.style.left = "40px";
                attack.style.bottom = Math.floor(50) + 'px';
                attack.innerHTML = `<img src="game/fireBall.gif"
                alt="">`

                document.getElementById('container').appendChild(attack);

                // 공격 이동
                moveAttack(attack)

                setTimeout(function () {
                    isAttacking = false;
                }, 100);
            }

            // 미사일 이동
            function moveAttack(attack) {
                let attackAccel = 0.1;
                let gravity = 5;
                bottom = 30;
                let attackMoveInterval = setInterval(function () {
                    let attackLeft = parseInt(window.getComputedStyle(attack).getPropertyValue('left'));
                    if (attackLeft > 650) {
                        --AttackCnt;
                        attack.remove();
                    }


                    if (bottom <= GROUND_HEIGHT - 10) {
                        bottom += 30;
                    }
                    bottom -= gravity;

                    attack.style.bottom = bottom + 'px';

                    attackAccel += 1;
                    attack.style.left = attackLeft + attackAccel + 'px';

                    if (AttackCnt < 1) {
                        clearInterval(attackMoveInterval);
                    }
                }, 20)
            }

            let shellAttackCnt = 0;
            let isShellAttack = false;

            // 등껍질 공격 생성
            function turtleShellAttack() {
                shellAttackCnt++;

                //isFirstAttack = true;
                isShellAttack = true;

                const attack = document.createElement('div');
                attack.classList.add("shellAttack");
                attack.style.width = "30px";
                attack.style.height = "30px";
                attack.style.position = "absolute";
                attack.style.left = "40px";
                attack.style.bottom = 47 + 'px';
                attack.innerHTML = `<img src="game/shell.gif"
                alt="">`

                document.getElementById('container').appendChild(attack);

                // 공격 이동
                moveShellAttack(attack);

                setTimeout(function () {
                    isShellAttack = false;
                }, 100);
            }

            // 등껍질 공격 이동
            function moveShellAttack(attack) {

                let attackMoveInterval = setInterval(function () {
                    let attackLeft = parseInt(window.getComputedStyle(attack).getPropertyValue('left')); //
                    if (attackLeft > 650) {
                        --shellAttackCnt;
                        attack.remove();
                    }

                    attack.style.left = attackLeft + 10 + 'px';

                    if (shellAttackCnt < 1) {
                        clearInterval(attackMoveInterval);
                    }
                }, 20)
            }

            // 미사일 적 상호작용
            function missileAttackHitCheck(enemyLeft, enemy) {
                if (AttackCnt > 0) { // 화면에 공격이 존재할때
                    let attackList = document.getElementsByClassName('attack');
                    let attackLeft = parseInt(window.getComputedStyle(attackList[0]).getPropertyValue('left'));

                    if (enemyLeft < attackLeft + 50) {
                        showExplosionEffect(attackLeft);
                        enemy.remove();
                        attackList[0].remove();
                        AttackCnt--; // 적과 충돌시 공격도 사라진다.
                    }
                }
            }

            // 등껍질 적 상호작용
            function shellAttackHitCheck(enemyLeft, enemy) {
                if (shellAttackCnt > 0) { // 화면에 공격이 존재할때

                    let attackList = document.getElementsByClassName('shellAttack');
                    let attackLeft = parseInt(window.getComputedStyle(attackList[0]).getPropertyValue('left'));

                    if (enemyLeft < attackLeft + 50) {
                        point += 100;

                        showExplosionEffect(attackLeft);
                        enemy.remove();
                        pointBox.innerText = "점수 : " + point;
                    }
                }
            }

            /*
            =================================================================================================================================================================================

                                    적군 관련 함수들

            =================================================================================================================================================================================
            */

            /**
             * 적1 생성 및 캐릭터, 적, 공격 상호작용
             * 
             * 이 함수의 구조는
             * 1. 적 객체 생성
             * 2. 적 이동 (setInterval을 사용해 계속 실행)
             *  2. 1. 적 아군 상호작용 (무사히 상호작용 완료시 인터벌 종료)
             *  2. 2. 적 공격 상호작용
             *  3. 인터벌 숫자를 점수에 따라 조절(이동속도)
             * 4. 함수 재귀. setTimeout을 사용하여 일정 시간 후에 재귀하며 '일정시간'은 점수에 따라 달라짐
             * 
             * /////////////////////////////////////////////////////////////////////////////////
             **/

            // 적 속성 정의
            function gumba() {
                const enemy = document.createElement('div');
                enemy.innerHTML = `<img src="game/Anigoomba.webp"
                alt="">`
                enemy.classList.add('enemy');
                enemy.style.width = '30px';
                enemy.style.height = '30px';
                enemy.style.position = 'absolute';
                enemy.style.left = '670px';
                enemyHeight = Math.floor(GROUND_HEIGHT);
                enemy.style.bottom = enemyHeight + 'px';
                return enemy;
            }

            let isCreateKoopa = false
            function createEnemy() {
                // 적 객체 생성

                let enemy = gumba();
                document.getElementById('container').appendChild(enemy); // 적 속성 삽입
                // 적 이동
                let moveLeftInterval = setInterval(function () {

                    if (point >= 6000 && isCreateKoopa == false) {
                        createkingKoopa();
                        isCreateKoopa = true;

                    }


                    let enemyLeft = parseInt(window.getComputedStyle(enemy).getPropertyValue('left'));
                    let enemyBottom = parseInt(window.getComputedStyle(enemy).getPropertyValue('bottom'));


                    // 히트박스 정의
                    let characterHitboxTop = currentPosition + 30;
                    let enemyHitboxTop = enemyBottom + 25;

                    // 캐릭터, 적 상호작용부 =========================================================
                    // 적이 끝까지 가면 적 삭제하고 점수 up
                    if (enemyLeft <= 0) {
                        enemy.remove();
                        clearInterval(moveLeftInterval);
                        point += 100;
                        pointBox.innerText = "점수 : " + point;

                    } else if (enemyLeft > 30 && enemyLeft <= 60 && currentPosition < enemyHitboxTop) { // 캐릭터, 적 충돌 감지
                        // 히트박스 좌측 좌표 // 히트박스 오른쪽 좌표 // 캐릭터 히트박스 상단(숫자) // 적 히트박스 상단(숫자)
                        if (isMarioTrample === true) {
                            enemy.remove();
                            clearInterval(moveLeftInterval);
                            point += 200;
                            pointBox.innerText = "점수 : " + point;
                            showtrampleEffect(enemyHeight);
                        } else {
                            isGameOver = true;
                            clearInterval(moveLeftInterval);
                            gameOver();
                            return;
                        }
                    } else { // 적 이동
                        if (isGameOver === false) {
                            enemy.style.left = enemyLeft - 5 + 'px';
                        }
                    }
                    missileAttackHitCheck(enemyLeft, enemy);
                    shellAttackHitCheck(enemyLeft, enemy);

                    // 이동속도 생성
                }, moveSpeed(point, [20, 15, 10, 7], [5, 5, 10, 8])); // 랜덤한 간격으로 이동

                // 재귀함수지만 별개의 스레드를 사용하기 때문에 실행스택이 쌓이지 않는다. (새로 실행된 함수의 실행여부와 상관없이 함수가 끝난다.)
                theFunc = createEnemy;
                respownTime(theFunc, [1000, 700, 700, 700], [2500, 1500, 500, 500]);
                // 순서대로 (실행할 함수, 난이도별 생성시간, 난이도별 랜덤 추가값)
            }
            // ↑ 굼바

            /**
             * 적2 생성 및 캐릭터, 적 상호작용
             * 
             * 이 함수의 구조는
             * 1. 적 객체 생성
             * 2. 적 이동 (setInterval을 사용해 계속 실행)
             *  2. 1. 적 아군 상호작용 (무사히 상호작용 완료시 인터벌 종료)
             *  3. 인터벌 숫자를 점수에 따라 조절(이동속도)
             * 4. 함수 재귀. setTimeout을 사용하여 일정 시간 후에 재귀하며 '일정시간'은 점수에 따라 달라짐
             * 
             * /////////////////////////////////////////////////////////////////////////////////
             **/
            function killer() {
                const enemy = document.createElement('div');//
                enemy.innerHTML = `<img src="game/smallKillerDot.png"
                alt="">`
                enemy.classList.add('enemy2');
                enemy.style.width = '60px';
                enemy.style.height = '50px';
                enemy.style.position = 'absolute';
                enemy.style.left = '650px';
                enemy.style.border = 'lpx solid black;'
                enemyHeight = Math.floor(GROUND_HEIGHT + 35);
                enemy.style.bottom = enemyHeight + Math.floor(Math.random() * 50) + 'px';
                return enemy;
            }
            function createEnemy2() {
                // 적 생성부
                let enemy = killer();
                //enemy.style.bottom = 81 + 'px';

                // 적 속성 불러오기
                document.getElementById('container').appendChild(enemy);
                let moveLeftInterval = setInterval(function () {
                    let enemyLeft = parseInt(window.getComputedStyle(enemy).getPropertyValue('left'));
                    let enemyBottom = parseInt(window.getComputedStyle(enemy).getPropertyValue('bottom'));


                    // 히트박스 정의
                    let characterHitboxTop = currentPosition + 30;
                    let enemyHitboxTop = enemyBottom + 25;

                    enemyBottom -= 2;

                    // 캐릭터, 적 상호작용부 =========================================================
                    // 적이 끝까지 가면 적 삭제하고 점수 up

                    if (enemyLeft <= 0) {
                        enemy.remove();
                        clearInterval(moveLeftInterval);
                        point += 100;
                        pointBox.innerText = "점수 : " + point;

                    } else if (enemyLeft > 5 && enemyLeft <= 50 && (characterHitboxTop >= enemyBottom && enemyHitboxTop >= currentPosition)) { // 캐릭터, 적 충돌 감지
                        // 히트박스 좌측 좌표 // 히트박스 오른쪽 좌표

                        if (isMarioTrample === true && currentPosition > enemyHitboxTop - 10) {
                            enemy.remove();
                            clearInterval(moveLeftInterval);
                            point += 200;
                            pointBox.innerText = "점수 : " + point;

                            showtrampleEffect(enemyHeight + 25);
                        } else {
                            clearInterval(moveLeftInterval);
                            gameOver();
                            return;
                        }

                    } else { // 적 이동
                        if (isGameOver === false) {
                            enemy.style.left = enemyLeft - 5 + 'px';
                        }
                    }
                    //===================================================================

                    // 이동속도 생성
                }, moveSpeed(point, [20, 10, 7, 7], [5, 5, 10, 10]));

                theFunc = createEnemy2;
                respownTime(theFunc, [10000, 7000, 5000, 3000], [0, 1500, 500, 500]);
            }
            // ↑ 포탄


            /**
             * 적3 생성 및 캐릭터, 적, 공격 상호작용
             * 
             * 이 함수의 구조는
             * 1. 적 객체 생성
             * 2. 적 이동 (setInterval을 사용해 계속 실행)
             *  2. 1. 적 아군 상호작용 (무사히 상호작용 완료시 인터벌 종료)
             *  2. 2. 적 공격 상호작용
             *  3. 인터벌 숫자를 점수에 따라 조절(이동속도)
             * 4. 함수 재귀. setTimeout을 사용하여 일정 시간 후에 재귀하며 '일정시간'은 점수에 따라 달라짐
             * 
             * /////////////////////////////////////////////////////////////////////////////////
             **/

            // 적 속성 정의
            function turtle() {
                const enemy = document.createElement('div');
                enemy.innerHTML = `<img src="game/turtle.png"
                alt="">`
                enemy.classList.add('turtle');
                enemy.style.width = '40px';
                enemy.style.height = '40px';
                enemy.style.position = 'absolute';
                enemy.style.left = '670px';
                enemyHeight = Math.floor(GROUND_HEIGHT);
                enemy.style.bottom = enemyHeight + 'px';
                return enemy;
            }

            // 거북이 생성
            function createTurtle() {
                // 적 객체 생성

                let enemy = turtle();
                document.getElementById('container').appendChild(enemy); // 적 속성 삽입
                // 적 이동

                let isLastAttack = false
                let moveLeftInterval = setInterval(function () {

                    let enemyLeft = parseInt(window.getComputedStyle(enemy).getPropertyValue('left'));
                    let enemyBottom = parseInt(window.getComputedStyle(enemy).getPropertyValue('bottom'));


                    // 히트박스 정의
                    let characterHitboxTop = currentPosition + 30;
                    let enemyHitboxTop = enemyBottom + 30;

                    // 캐릭터, 적 상호작용부 =========================================================
                    // 적이 끝까지 가면 적 삭제하고 점수 up
                    if (enemyLeft <= 0) {
                        enemy.remove();
                        clearInterval(moveLeftInterval);
                        point += 100;
                        pointBox.innerText = "점수 : " + point;

                    } else if (enemyLeft > 20 && enemyLeft <= 60 && currentPosition < enemyHitboxTop) { // 캐릭터, 적 충돌 감지
                        // 히트박스 좌측 좌표 // 히트박스 오른쪽 좌표 // 캐릭터 히트박스 상단(숫자) // 적 히트박스 상단(숫자)
                        if (isMarioTrample === true) {
                            enemy.remove();
                            clearInterval(moveLeftInterval);
                            pointBox.innerText = "점수 : " + point;
                            showtrampleEffect(enemyHeight);

                            setTimeout(function () {
                                turtleShellAttack();
                                point += 200;
                                pointBox.innerText = "점수 : " + point;

                            }, 20);

                        } else {
                            isGameOver = true;
                            clearInterval(moveLeftInterval);
                            gameOver();
                            return;
                        }
                    } else { // 적 이동
                        if (isGameOver === false) {
                            enemy.style.left = enemyLeft - 5 + 'px';
                        }
                    }


                    if (isKoopaDeath === true && isLastAttack === false) {
                        turtleShellAttack();
                        isLastAttack = true;
                    }

                    // 미사일 공격
                    missileAttackHitCheck(enemyLeft, enemy);

                    // 거북이등껍질공격
                    shellAttackHitCheck(enemyLeft, enemy);


                    // 이동속도 생성
                }, moveSpeed(point, [20, 15, 10, 7], [5, 5, 10, 8]));

                theFunc = createTurtle;

                respownTime(theFunc, [5000, 3000, 1000, 1000], [2500, 1500, 500, 500]);

            }
            // ↑ 거북이


            // 보스 // // // // // //
            function kingKoopa() {
                const enemy = document.createElement('div');
                enemy.innerHTML = `<img src="game/kingKoopa.gif"
                alt="">`
                enemy.classList.add('koopa');
                enemy.style.width = '70px';
                enemy.style.height = '70px';
                enemy.style.position = 'absolute';
                enemy.style.left = '670px';
                enemyHeight = Math.floor(GROUND_HEIGHT);
                enemy.style.bottom = enemyHeight + 'px';
                return enemy;
            }

            // 쿠파

            function createkingKoopa() {
                // 적 객체 생성

                let healthBar = document.getElementById("koopaHealthBar");
                let koopaHealthBarBack = document.getElementById("koopaHealthBarBack");

                healthBar.style.display = 'block';
                koopaHealthBarBack.style.display = 'block';

                let intervalCnt = 0;
                let enemy = kingKoopa();

                let hp = 100;

                document.getElementById('container').appendChild(enemy); // 적 속성 삽입
                // 적 이동
                let moveLeftInterval = setInterval(function () {

                    let enemyLeft = parseInt(window.getComputedStyle(enemy).getPropertyValue('left'));
                    let enemyBottom = parseInt(window.getComputedStyle(enemy).getPropertyValue('bottom'));


                    // 히트박스 정의
                    let characterHitboxTop = currentPosition + 30;
                    let enemyHitboxTop = enemyBottom + 30;

                    // 캐릭터, 적 상호작용부 =========================================================
                    // 적이 끝까지 가면 적 삭제하고 점수 up
                    if (enemyLeft <= 0) {
                        enemy.remove();
                        clearInterval(moveLeftInterval);
                        point += 100;
                        pointBox.innerText = "점수 : " + point;

                    } else if (enemyLeft <= 60) { // 캐릭터, 적 충돌 감지
                        // 히트박스 좌측 좌표 // 히트박스 오른쪽 좌표 // 캐릭터 히트박스 상단(숫자) // 적 히트박스 상단(숫자)

                        isGameOver = true;
                        clearInterval(moveLeftInterval);
                        gameOver();
                        return;

                    } else { // 적 이동
                        if (isGameOver === false) {
                            enemy.style.left = enemyLeft - 1 + 'px';
                        }
                    }

                    // 미사일
                    if (AttackCnt > 0) { // 화면에 공격이 존재할때
                        let attackList = document.getElementsByClassName('attack');
                        let attackLeft = parseInt(window.getComputedStyle(attackList[0]).getPropertyValue('left'));

                        if (enemyLeft < attackLeft + 50) {
                            showExplosionEffect(attackLeft);
                            hp -= 5;
                            attackList[0].remove();
                            AttackCnt--; // 적과 충돌시 공격도 사라진다.
                        }
                    }

                    // 등껍질
                    if (shellAttackCnt > 0) { // 화면에 공격이 존재할때

                        let attackList = document.getElementsByClassName('shellAttack');
                        let attackLeft = parseInt(window.getComputedStyle(attackList[0]).getPropertyValue('left'));

                        if (enemyLeft < attackLeft + 50) {
                            point += 100;
                            attackList[0].remove();
                            --shellAttackCnt;
                            showExplosionEffect(attackLeft);
                            hp -= 10;
                        }
                    }


                    healthBar.style.width = hp * 4 + 'px';

                    if (hp <= 0 && isKoopaDeath === false) {
                        point += 5000;
                        enemy.remove();
                        isKoopaDeath = true;
                        clearInterval(moveLeftInterval);
                        filter.style.backgroundColor = "rgba(0, 0, 0, 0)";
                        container.style.animation = 'slide 7s linear infinite';
                        healthBar.style.display = 'none';
                        koopaHealthBarBack.style.display = 'none';
                        setTimeout(function () {
                            gameOver();
                        }, 2000);

                    }

                    // 이동속도 생성
                    intervalCnt++;
                    if (intervalCnt % 10 === 0) {
                        createTurtle();
                        if (hp <= 50) {
                            for (let i = 0; i < 1; ++i) {
                                createTurtle();
                            }
                        }
                    }

                }, 100);
            }




            // ========================================================================================================================
            // 기능 함수들


            function respownTime(theFunc, timeList, randomList) {

                if (isGameOver === false) {


                    // 생성간격
                    if (isCreateKoopa === true) {
                        filter.style.backgroundColor = "rgba(150, 100, 50, 0.7)";
                        container.style.animation = 'slide 7s linear infinite';
                    } else if (point < 1000) {
                        setTimeout(theFunc, timeList[0] + Math.floor(Math.random() * randomList[0]));
                    } else if (point < 2500) {
                        setTimeout(theFunc, timeList[1] + Math.floor(Math.random() * randomList[1]));
                        container.style.animation = 'slide 4s linear infinite';
                        filter.style.backgroundColor = "rgba(0, 0, 0, 0.226)";
                    } else if (point < 4000) {
                        setTimeout(theFunc, timeList[2] + Math.floor(Math.random() * randomList[2]));
                        filter.style.backgroundColor = "rgba(255, 0, 0, 0.356)";
                        container.style.animation = 'slide 2s linear infinite';
                    } else {
                        setTimeout(theFunc, timeList[3] + Math.floor(Math.random() * randomList[3]));
                        filter.style.backgroundColor = "rgba(0, 0, 0, 0.9)";
                        container.style.animation = 'slide 1.5s linear infinite';
                    }
                }
            }
            function moveSpeed(point, speedArr, randArr) {
                let result =
                    point < 1000 ? speedArr[0] + Math.floor(Math.random() * randArr[0]) :
                        point < 2500 ? speedArr[1] + Math.floor(Math.random() * randArr[1]) :
                            point < 4000 ? speedArr[2] + Math.floor(Math.random() * randArr[2]) :
                                speedArr[3] + Math.floor(Math.random() * randArr[3]);
                return result;
            }


            /*
            =================================================================================================================================================================================

                                    

            =================================================================================================================================================================================
            */





            container = document.getElementById("container");
            filter = document.getElementById("filter");

            // 함수 최초실행
            createEnemy();

            setTimeout(function () {
                createTurtle();
            }, 4000);

            setTimeout(function () {
                createEnemy2();
            }, 7000);

            /**
             * 키 입력 감지 함수
             **/
            document.addEventListener('keydown', function (event) {
                if (isGameOver === false) {
                    if (event.code === 'Space' && !isJumping) {
                        jump();
                    }
                    if (event.code === 'KeyE' && !isAttacking && point >= 200 && isKoopaDeath === false) {
                        point -= 200;
                        document.getElementById("point").innerText = "점수 : " + point;
                        characterAttack();
                    }
                }
            });
        }

        // 20 동안 이펙트 보여주는 함수
        function showtrampleEffect(height) {

            trampleEffect.style.bottom = height + "px";
            trampleEffect.style.display = "inline";
            setTimeout(function () {
                trampleEffect.style.display = "none";
            }, 20);
        }

        function showExplosionEffect(left) {
            explosionEffect.style.left = left + "px";
            explosionEffect.style.display = "inline";
            setTimeout(function () {
                explosionEffect.style.display = "none";
            }, 20);
        }

        function gameOver() {
            const gameOverPage = document.getElementById("gameOverPage");
            if (isKoopaDeath === false) {
                gameOverPage.innerHTML = `<p id="gameOverText">Game Over</p><button onclick="javascript:gameReStart();">시작 화면으로</button>`

            } else {
                gameOverPage.innerHTML = `<p id="gameOverText">You Win!</p><button onclick="javascript:gameReStart();">시작 화면으로</button>`

            }
            gameOverPage.style.display = 'block';
            printScore = document.getElementById("lastScore");
            printScore.innerHTML = "점수 : " + point;

            document.getElementById('point').style.display = 'none';
        }

        function gameReStart() {
            document.location.reload();
        }





        // 거북이 추가, 밟으면 등껍질 날아가며 마주치는 모든 굼바 제거 기능

        // 화면 개선
    </script>
</head>

<!--onload="init()-->
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<body>
	<c:import url="/include/Header.jsp" />
	
    <div style="margin-top: 50px;">
        <div id="container" style="z-index: -2">
            <div id="startPage">

                <button onclick="javascript:gameStart();">Game Start</button>
            </div>
            <p id="lastScore"></p>
            <div id="gameOverPage">
                <button onclick="javascript:gameReStart();">시작 화면으로</button>
            </div>
            <div id="filter" style="z-index: -1;"></div>
            <p id="point" style="color:rgba(255, 255, 255, 0.952); text-shadow: 0px 0px 2px #000000;">점수 : 0</p>
            <div id="character">
                <img src="game/mario.gif" alt="">
            </div>
            <div id="koopaHealthBarBack"></div>
            <div id="koopaHealthBar"></div>
            <div id="trampleEffect"></div>
            <div id="explosionEffect"></div>
        </div>
        <div id="manual">
            <p></p>
            점프 : SPACE<br>
            파이어볼 : E (200점 사용)
            <br><br>
            적 회피 + 100점<br>
            적 밟기 + 200점<br>
            <br>
            점수에 따라 난이도 증가.<br>
            보스 처치시 승리.
            <br><br><br><br><br>
            임기홍<br>
            igh0730@naver.com<br>
            https://github.com/OQOOO/webGameProject<br><br>
            <p></p>
        </div>
        <input id="cheat" type="number" value="0">
    </div>
</body>

</html>