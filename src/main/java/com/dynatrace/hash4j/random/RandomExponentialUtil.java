/*
 * Copyright 2022-2024 Dynatrace LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file includes a Java port of an algorithm for the generation of
 * exponentially distributed random values which is based on
 *
 * https://github.com/boostorg/random/blob/18999b33d9a64b574435668541a37ddefb2851df/include/boost/random/exponential_distribution.hpp
 *
 * and which was published with following copyright notes and under the
 * license given below:
 *
 *
 * Copyright Jens Maurer 2000-2001
 * Copyright Steven Watanabe 2011
 * Copyright Jason Rhinelander 2016
 * Distributed under the Boost Software License, Version 1.0. (See
 * accompanying file LICENSE_1_0.txt or copy at
 * http://www.boost.org/LICENSE_1_0.txt)
 *
 * See http://www.boost.org for most recent version including documentation.
 *
 *
 * Boost Software License - Version 1.0 - August 17th, 2003
 *
 * Permission is hereby granted, free of charge, to any person or organization
 * obtaining a copy of the software and accompanying documentation covered by
 * this license (the "Software") to use, reproduce, display, distribute,
 * execute, and transmit the Software, and to prepare derivative works of the
 * Software, and to permit third-parties to whom the Software is furnished to
 * do so, all subject to the following:
 *
 * The copyright notices in the Software and this entire statement, including
 * the above license grant, this restriction and the following disclaimer,
 * must be included in all copies of the Software, in whole or in part, and
 * all derivative works of the Software, unless such copies or derivative
 * works are solely in the form of machine-executable object code generated by
 * a source language processor.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDERS OR ANYONE DISTRIBUTING THE SOFTWARE BE LIABLE
 * FOR ANY DAMAGES OR OTHER LIABILITY, WHETHER IN CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package com.dynatrace.hash4j.random;

final class RandomExponentialUtil {

  private RandomExponentialUtil() {}

  static final double[] X = {
    8.69711747013105,
    7.69711747013105,
    6.941033629377213,
    6.47837849383257,
    6.144164665772473,
    5.8821443157954,
    5.666410167454034,
    5.4828906275260625,
    5.323090505754399,
    5.181487281301501,
    5.054288489981305,
    4.938777085901251,
    4.832939741025113,
    4.735242996601741,
    4.644491885420085,
    4.559737061707351,
    4.480211746528422,
    4.405287693473573,
    4.334443680317273,
    4.267242480277366,
    4.203313713735184,
    4.1423408656640515,
    4.084051310408298,
    4.028208544647937,
    3.9746060666737884,
    3.9230625001354897,
    3.873417670399509,
    3.8255294185223367,
    3.779270992411668,
    3.7345288940397974,
    3.691201090237419,
    3.6491955157608538,
    3.6084288131289095,
    3.5688252656483375,
    3.530315889129344,
    3.49283765477406,
    3.4563328211327606,
    3.4207483572511204,
    3.386035442460302,
    3.35214903090011,
    3.319047470970749,
    3.286692171599069,
    3.2550473085704503,
    3.2240795652862646,
    3.1937579032122407,
    3.1640533580259733,
    3.134938858084441,
    3.1063890623398245,
    3.0783802152540907,
    3.0508900166154556,
    3.0238975044556766,
    2.9973829495161306,
    2.9713277599210897,
    2.9457143948950457,
    2.920526286512741,
    2.895747768600142,
    2.8713640120155364,
    2.847360965635189,
    2.8237253024500353,
    2.8004443702507382,
    2.777506146439757,
    2.7548991965623455,
    2.732612636194701,
    2.710636095867929,
    2.688959688741804,
    2.667573980773267,
    2.6464699631518096,
    2.6256390267977885,
    2.6050729387408356,
    2.5847638202141408,
    2.5647041263169053,
    2.54488662711187,
    2.525304390037828,
    2.505950763528594,
    2.48681936174021,
    2.467904050297365,
    2.4491989329782498,
    2.4306983392644197,
    2.4123968126888706,
    2.3942890999214583,
    2.376370140536141,
    2.3586350574093373,
    2.341079147703035,
    2.3236978743901964,
    2.30648685828358,
    2.2894418705322694,
    2.272558825553155,
    2.255833774367219,
    2.2392628983129086,
    2.2228425031110364,
    2.2065690132576634,
    2.19043896672322,
    2.1744490099377747,
    2.1585958930438855,
    2.1428764653998416,
    2.127287671317368,
    2.1118265460190417,
    2.0964902118017146,
    2.0812758743932247,
    2.0661808194905755,
    2.051202409468585,
    2.0363380802487696,
    2.021585338318926,
    2.006941757894518,
    1.9924049782135764,
    1.9779727009573602,
    1.963642687789548,
    1.9494127580071845,
    1.9352807862970511,
    1.9212447005915276,
    1.907302480018387,
    1.8934521529393078,
    1.8796917950722107,
    1.8660195276928275,
    1.852433515911175,
    1.8389319670188793,
    1.8255131289035191,
    1.8121752885263902,
    1.7989167704602904,
    1.7857359354841253,
    1.772631179231305,
    1.7596009308890743,
    1.746643651946074,
    1.7337578349855711,
    1.720942002521935,
    1.7081947058780576,
    1.6955145241015377,
    1.6829000629175537,
    1.670349953716452,
    1.6578628525741725,
    1.6454374393037234,
    1.6330724165359911,
    1.6207665088282577,
    1.6085184617988582,
    1.5963270412864832,
    1.5841910325326887,
    1.5721092393862295,
    1.5600804835278879,
    1.5481036037145133,
    1.5361774550410319,
    1.524300908219226,
    1.5124728488721169,
    1.5006921768428165,
    1.4889578055167456,
    1.4772686611561334,
    1.4656236822457451,
    1.4540218188487932,
    1.4424620319720123,
    1.4309432929388795,
    1.4194645827699828,
    1.4080248915695353,
    1.3966232179170417,
    1.3852585682631218,
    1.3739299563284901,
    1.3626364025050866,
    1.351376933258335,
    1.3401505805295046,
    1.3289563811371163,
    1.3177933761763245,
    1.306660610415174,
    1.2955571316866008,
    1.2844819902750126,
    1.2734342382962411,
    1.2624129290696153,
    1.2514171164808525,
    1.2404458543344064,
    1.229498195693849,
    1.2185731922087903,
    1.2076698934267613,
    1.196787346088403,
    1.1859245934042024,
    1.1750806743109117,
    1.1642546227056791,
    1.1534454666557747,
    1.1426522275816728,
    1.1318739194110787,
    1.1211095477013306,
    1.1103581087274115,
    1.0996185885325978,
    1.0888899619385473,
    1.0781711915113728,
    1.067461226479968,
    1.0567590016025519,
    1.0460634359770447,
    1.035373431790529,
    1.0246878730026179,
    1.0140056239570971,
    1.0033255279156974,
    0.9926464055072765,
    0.9819670530850632,
    0.9712862409839039,
    0.9606027116686671,
    0.9499151777640766,
    0.939222319955263,
    0.9285227847472112,
    0.917815182070045,
    0.907098082715691,
    0.8963700155898907,
    0.8856294647617523,
    0.8748748662910258,
    0.8641046048110052,
    0.853317009842374,
    0.8425103518103693,
    0.8316828377342739,
    0.8208326065544125,
    0.8099577240574191,
    0.7990561773554878,
    0.7881258688694932,
    0.7771646097591305,
    0.7661701127354354,
    0.7551399841819829,
    0.7440717155005088,
    0.7329626735843661,
    0.7218100903087569,
    0.7106110509096557,
    0.6993624811032326,
    0.6880611327737486,
    0.6767035680295234,
    0.6652861413926786,
    0.6538049798476656,
    0.642255960424537,
    0.630634684933491,
    0.6189364513948767,
    0.6071562216203008,
    0.5952885842915036,
    0.5833277127487703,
    0.571267316532589,
    0.5591005855115413,
    0.5468201251633111,
    0.5344178812371662,
    0.5218850515921356,
    0.509211982443655,
    0.4963880455186716,
    0.48340149165346225,
    0.47023927508216945,
    0.45688684093142073,
    0.44332786607355296,
    0.4295439402254113,
    0.415514169600357,
    0.4012146788962784,
    0.38661797794112024,
    0.37169214532991784,
    0.3563997602583944,
    0.3406964810648498,
    0.32452911701691006,
    0.3078329546749329,
    0.29052795549123117,
    0.2725131854784655,
    0.25365836338591286,
    0.23379048305967554,
    0.21267151063096745,
    0.18995868962243279,
    0.1651276225641883,
    0.1373049809400138,
    0.10483850756582018,
    0.06385216381500348,
    0.0
  };

  static final double[] Y = {
    0.0,
    4.5413435384149677E-4,
    9.672692823271745E-4,
    0.0015362997803015724,
    0.0021459677437189063,
    0.002788798793574076,
    0.003460264777836904,
    0.004157295120833795,
    0.004877655983542392,
    0.005619642207205483,
    0.006381905937319179,
    0.007163353183634984,
    0.00796307743801704,
    0.008780314985808975,
    0.00961441364250221,
    0.010464810181029979,
    0.011331013597834597,
    0.012212592426255381,
    0.013109164931254991,
    0.014020391403181938,
    0.014945968011691148,
    0.015885621839973163,
    0.016839106826039948,
    0.01780620041091136,
    0.01878670074469603,
    0.019780424338009743,
    0.020787204072578117,
    0.02180688750428358,
    0.02283933540638524,
    0.02388442051155817,
    0.024942026419731783,
    0.026012046645134217,
    0.0270943837809558,
    0.028188948763978636,
    0.029295660224637393,
    0.030414443910466604,
    0.03154523217289361,
    0.032687963508959535,
    0.03384258215087433,
    0.03500903769739741,
    0.03618728478193142,
    0.03737728277295936,
    0.03857899550307486,
    0.039792391023374125,
    0.04101744138041482,
    0.042254122413316234,
    0.04350241356888818,
    0.04476229773294328,
    0.04603376107617517,
    0.04731679291318155,
    0.0486113855733795,
    0.04991753428270637,
    0.05123523705512628,
    0.05256449459307169,
    0.05390531019604609,
    0.05525768967669704,
    0.05662164128374288,
    0.05799717563120066,
    0.059384305633420266,
    0.06078304644547963,
    0.062193415408540995,
    0.06361543199980733,
    0.06504911778675375,
    0.06649449638533977,
    0.0679515934219366,
    0.06942043649872875,
    0.07090105516237183,
    0.07239348087570874,
    0.07389774699236475,
    0.07541388873405841,
    0.0769419431704805,
    0.07848194920160642,
    0.0800339475423199,
    0.08159798070923742,
    0.08317409300963238,
    0.08476233053236812,
    0.08636274114075691,
    0.08797537446727022,
    0.08960028191003286,
    0.09123751663104016,
    0.09288713355604354,
    0.09454918937605586,
    0.0962237425504328,
    0.0979108533114922,
    0.09961058367063713,
    0.10132299742595363,
    0.10304816017125772,
    0.10478613930657017,
    0.10653700405000166,
    0.1083008254510338,
    0.11007767640518538,
    0.1118676316700563,
    0.11367076788274431,
    0.11548716357863353,
    0.11731689921155557,
    0.11916005717532768,
    0.12101672182667483,
    0.12288697950954514,
    0.12477091858083096,
    0.12666862943751067,
    0.12858020454522817,
    0.13050573846833077,
    0.13244532790138752,
    0.13439907170221363,
    0.13636707092642886,
    0.1383494288635802,
    0.14034625107486245,
    0.1423576454324722,
    0.14438372216063478,
    0.14642459387834494,
    0.1484803756438668,
    0.1505511850010399,
    0.15263714202744286,
    0.15473836938446808,
    0.15685499236936523,
    0.1589871389693142,
    0.16113493991759203,
    0.16329852875190182,
    0.165478041874936,
    0.1676736186172502,
    0.16988540130252766,
    0.17211353531532006,
    0.1743581691713535,
    0.17661945459049488,
    0.1788975465724783,
    0.1811926034754963,
    0.18350478709776746,
    0.1858342627621971,
    0.18818119940425432,
    0.1905457696631954,
    0.19292814997677135,
    0.19532852067956322,
    0.19774706610509887,
    0.20018397469191127,
    0.20263943909370902,
    0.2051136562938377,
    0.20760682772422204,
    0.21011915938898826,
    0.21265086199297828,
    0.21520215107537868,
    0.21777324714870053,
    0.2203643758433595,
    0.2229757680581202,
    0.22560766011668407,
    0.2282602939307167,
    0.2309339171696274,
    0.23362878343743335,
    0.23634515245705964,
    0.23908329026244918,
    0.24184346939887721,
    0.2446259691318921,
    0.24743107566532763,
    0.2502590823688623,
    0.25311029001562946,
    0.2559850070304154,
    0.25888354974901623,
    0.261806242689363,
    0.2647534188350622,
    0.2677254199320448,
    0.27072259679906,
    0.27374530965280297,
    0.27679392844851736,
    0.2798688332369729,
    0.28297041453878075,
    0.2860990737370768,
    0.28925522348967775,
    0.2924392881618926,
    0.2956517042812612,
    0.2988929210155818,
    0.3021634006756935,
    0.30546361924459026,
    0.3087940669345602,
    0.31215524877417955,
    0.31554768522712895,
    0.31897191284495724,
    0.32242848495608917,
    0.3259179723935562,
    0.3294409642641363,
    0.332998068761809,
    0.3365899140286776,
    0.34021714906678,
    0.3438804447045024,
    0.347580494621637,
    0.35131801643748334,
    0.35509375286678746,
    0.3589084729487498,
    0.3627629733548178,
    0.36665807978151416,
    0.370594648435146,
    0.37457356761590216,
    0.3785957594095808,
    0.38266218149600983,
    0.38677382908413765,
    0.3909317369847971,
    0.39513698183329016,
    0.3993906844752311,
    0.4036940125305303,
    0.4080481831520324,
    0.4124544659971612,
    0.4169141864330029,
    0.4214287289976166,
    0.42599954114303434,
    0.43062813728845883,
    0.4353161032156366,
    0.4400651008423539,
    0.4448768734145485,
    0.449753251162755,
    0.4546961574746155,
    0.4597076156421377,
    0.4647897562504262,
    0.46994482528396,
    0.4751751930373774,
    0.4804833639304542,
    0.4858719873418849,
    0.49134386959403253,
    0.49690198724154955,
    0.5025495018413477,
    0.5082897764106429,
    0.5141263938147486,
    0.5200631773682336,
    0.5261042139836197,
    0.5322538802630433,
    0.5385168720028619,
    0.5448982376724396,
    0.5514034165406413,
    0.5580382822625874,
    0.5648091929124002,
    0.5717230486648258,
    0.578787358602845,
    0.586010318477268,
    0.5934009016917334,
    0.6009689663652322,
    0.608725382079622,
    0.6166821809152077,
    0.624852738703666,
    0.6332519942143661,
    0.6418967164272661,
    0.6508058334145711,
    0.6600008410789997,
    0.6695063167319247,
    0.6793505722647654,
    0.689566496117078,
    0.7001926550827882,
    0.711274760805076,
    0.722867659593572,
    0.7350380924314235,
    0.7478686219851951,
    0.7614633888498963,
    0.7759568520401156,
    0.7915276369724956,
    0.8084216515230084,
    0.8269932966430503,
    0.8477855006239896,
    0.8717043323812036,
    0.9004699299257465,
    0.9381436808621747,
    1.0
  };

  // use of strictfp to make random values platform-independent
  public static strictfp double exponential(PseudoRandomGenerator prg) {
    double shift = 0;
    for (; ; ) {
      long randomLong = prg.nextLong();
      int i = (int) randomLong & 0xFF;
      double x = (randomLong >>> 11) * 0x1.0p-53 * X[i];
      if (x < X[i + 1]) {
        return shift + x;
      }
      // For i=0 we need to generate from the tail, but because this is an exponential
      // distribution, the tail looks exactly like the body, so we can simply repeat with a
      // shift:
      if (i == 0) {
        shift += X[1];
      } else {
        double y01 = prg.nextDouble();
        double y = Y[i] + y01 * (Y[i + 1] - Y[i]);

        // All we care about is whether these are < or > 0; these values are equal to
        // (lbound) or proportional to (ubound) `y` minus the lower/upper bound.
        double yAboveUbound = (X[i] - X[i + 1]) * y01 - (X[i] - x);
        double yAboveLbound = y - (Y[i + 1] + (X[i + 1] - x) * Y[i + 1]);

        if (yAboveUbound < 0 // if above the upper bound reject immediately
            && (yAboveLbound < 0 // If below the lower bound accept immediately
                || y
                    < StrictMath.exp(
                        -x) // Otherwise it's between the bounds and we need a full check, use
            // strict math to make random values platform independent
            )) {
          return x + shift;
        }
      }
    }
  }

  // visible for testing
  static double getX(int i) {
    return X[i];
  }

  // visible for testing
  static double getY(int i) {
    return Y[i];
  }
}
