/*
 * Copyright (c) 2015-2018 Petr Zelenka <petr.zelenka@sellcom.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sellcom.core.internal.io.encoding;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.sellcom.core.internal.test.TestUtils;
import org.sellcom.core.internal.util.RandomUtils;
import org.sellcom.core.io.encoding.BinaryDecoder;
import org.sellcom.core.io.encoding.BinaryEncoder;
import org.sellcom.core.io.encoding.StandardBinaryEncodings;

public class Base58FlickrEncodingTest {

	private static final BinaryDecoder DECODER = StandardBinaryEncodings.createBase58FlickrDecoder();

	private static final BinaryEncoder ENCODER = StandardBinaryEncodings.createBase58FlickrEncoder();

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class CompatibilityTest {

		private BigInteger decodedValue;

		private String encodedValue;


		public CompatibilityTest(ParameterValues values) {
			encodedValue = values.getEncodedValue();
			decodedValue = values.getDecodedValue();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					new ParameterValues(BigInteger.valueOf(3469722931L), "6hBeKa"),
					new ParameterValues(BigInteger.valueOf(3469727167L), "6hBg1c"),
					new ParameterValues(BigInteger.valueOf(3469729381L), "6hBgEn"),
					new ParameterValues(BigInteger.valueOf(3469733423L), "6hBhS4"),
					new ParameterValues(BigInteger.valueOf(3469741543L), "6hBkh4"),
					new ParameterValues(BigInteger.valueOf(3469744991L), "6hBmiv"),
					new ParameterValues(BigInteger.valueOf(3469745237L), "6hBmnK"),
					new ParameterValues(BigInteger.valueOf(3469746485L), "6hBmKg"),
					new ParameterValues(BigInteger.valueOf(3469749801L), "6hBnJr"),
					new ParameterValues(BigInteger.valueOf(3469751649L), "6hBohi"),
					new ParameterValues(BigInteger.valueOf(3469755057L), "6hBpi4"),
					new ParameterValues(BigInteger.valueOf(3469761455L), "6hBrcn"),
					new ParameterValues(BigInteger.valueOf(3469774581L), "6hBv6F"),
					new ParameterValues(BigInteger.valueOf(3469787029L), "6hByNi"),
					new ParameterValues(BigInteger.valueOf(3469794165L), "6hBAVk"),
					new ParameterValues(BigInteger.valueOf(3469803421L), "6hBDEV"),
					new ParameterValues(BigInteger.valueOf(3469830629L), "6hBML2"),
					new ParameterValues(BigInteger.valueOf(3469844075L), "6hBRKR"),
					new ParameterValues(BigInteger.valueOf(3469849157L), "6hBTgt"),
					new ParameterValues(BigInteger.valueOf(3469850245L), "6hBTAe"),
					new ParameterValues(BigInteger.valueOf(3469852775L), "6hBUkR"),
					new ParameterValues(BigInteger.valueOf(3469864417L), "6hBXNz"),
					new ParameterValues(BigInteger.valueOf(3469869661L), "6hBZmZ"),
					new ParameterValues(BigInteger.valueOf(3469869693L), "6hBZnx"),
					new ParameterValues(BigInteger.valueOf(3469872055L), "6hC15g"),
					new ParameterValues(BigInteger.valueOf(3469872193L), "6hC17D"),
					new ParameterValues(BigInteger.valueOf(3469874013L), "6hC1E2"),
					new ParameterValues(BigInteger.valueOf(3469874901L), "6hC1Vk"),
					new ParameterValues(BigInteger.valueOf(3469876247L), "6hC2jx"),
					new ParameterValues(BigInteger.valueOf(3469883113L), "6hC4mV"),
					new ParameterValues(BigInteger.valueOf(3469883585L), "6hC4v4"),
					new ParameterValues(BigInteger.valueOf(3469888341L), "6hC5V4"),
					new ParameterValues(BigInteger.valueOf(3469890469L), "6hC6xK"),
					new ParameterValues(BigInteger.valueOf(3469895737L), "6hC87z"),
					new ParameterValues(BigInteger.valueOf(3469901279L), "6hC9L8"),
					new ParameterValues(BigInteger.valueOf(3469903555L), "6hCarn"),
					new ParameterValues(BigInteger.valueOf(3469905409L), "6hCaZk"),
					new ParameterValues(BigInteger.valueOf(3469906325L), "6hCbg8"),
					new ParameterValues(BigInteger.valueOf(3469908939L), "6hCc3c"),
					new ParameterValues(BigInteger.valueOf(3469912243L), "6hCd2a"),
					new ParameterValues(BigInteger.valueOf(3469924937L), "6hCgN2"),
					new ParameterValues(BigInteger.valueOf(3469925109L), "6hCgQZ"),
					new ParameterValues(BigInteger.valueOf(3469928151L), "6hChKr"),
					new ParameterValues(BigInteger.valueOf(3469940145L), "6hCmje"),
					new ParameterValues(BigInteger.valueOf(3469944383L), "6hCnzi"),
					new ParameterValues(BigInteger.valueOf(3469947165L), "6hCopg"),
					new ParameterValues(BigInteger.valueOf(3469947757L), "6hCozt"),
					new ParameterValues(BigInteger.valueOf(3469953985L), "6hCqqR"),
					new ParameterValues(BigInteger.valueOf(3469954397L), "6hCqxX"),
					new ParameterValues(BigInteger.valueOf(3469956553L), "6hCrc8"),
					new ParameterValues(BigInteger.valueOf(3469961809L), "6hCsKK"),
					new ParameterValues(BigInteger.valueOf(3469964097L), "6hCtrc"),
					new ParameterValues(BigInteger.valueOf(3469966999L), "6hCuie"),
					new ParameterValues(BigInteger.valueOf(3469970917L), "6hCvsM"),
					new ParameterValues(BigInteger.valueOf(3469975763L), "6hCwUk"),
					new ParameterValues(BigInteger.valueOf(3469977815L), "6hCxvH"),
					new ParameterValues(BigInteger.valueOf(3469979041L), "6hCxSR"),
					new ParameterValues(BigInteger.valueOf(3469979153L), "6hCxUM"),
					new ParameterValues(BigInteger.valueOf(3469985123L), "6hCzFH"),
					new ParameterValues(BigInteger.valueOf(3469986263L), "6hCA2n"),
					new ParameterValues(BigInteger.valueOf(3469990821L), "6hCBnX"),
					new ParameterValues(BigInteger.valueOf(3469993533L), "6hCCbH"),
					new ParameterValues(BigInteger.valueOf(3469999751L), "6hCE2V"),
					new ParameterValues(BigInteger.valueOf(3470007957L), "6hCGtp"),
					new ParameterValues(BigInteger.valueOf(3470016843L), "6hCK7B"),
					new ParameterValues(BigInteger.valueOf(3470019133L), "6hCKN6"),
					new ParameterValues(BigInteger.valueOf(3470023731L), "6hCMan"),
					new ParameterValues(BigInteger.valueOf(3470031783L), "6hCPyc"),
					new ParameterValues(BigInteger.valueOf(3470034593L), "6hCQoD"),
					new ParameterValues(BigInteger.valueOf(3470042991L), "6hCSTr"),
					new ParameterValues(BigInteger.valueOf(3470047631L), "6hCUgr"),
					new ParameterValues(BigInteger.valueOf(3470051585L), "6hCVrB"),
					new ParameterValues(BigInteger.valueOf(3470053055L), "6hCVSX"),
					new ParameterValues(BigInteger.valueOf(3470061557L), "6hCYpx"),
					new ParameterValues(BigInteger.valueOf(3470067839L), "6hD1gR"),
					new ParameterValues(BigInteger.valueOf(3470068617L), "6hD1vg"),
					new ParameterValues(BigInteger.valueOf(3470077973L), "6hD4hz"),
					new ParameterValues(BigInteger.valueOf(3470084015L), "6hD65K"),
					new ParameterValues(BigInteger.valueOf(3470084095L), "6hD678"),
					new ParameterValues(BigInteger.valueOf(3470091783L), "6hD8oF"),
					new ParameterValues(BigInteger.valueOf(3470094097L), "6hD95z"),
					new ParameterValues(BigInteger.valueOf(3470110113L), "6hDdQH"),
					new ParameterValues(BigInteger.valueOf(3470113161L), "6hDeKg"),
					new ParameterValues(BigInteger.valueOf(3470115415L), "6hDfq8"),
					new ParameterValues(BigInteger.valueOf(3470117911L), "6hDgaa"),
					new ParameterValues(BigInteger.valueOf(3470126173L), "6hDiBB"),
					new ParameterValues(BigInteger.valueOf(3470140429L), "6hDnRp"),
					new ParameterValues(BigInteger.valueOf(3470152229L), "6hDrmR"),
					new ParameterValues(BigInteger.valueOf(3470163357L), "6hDuEH"),
					new ParameterValues(BigInteger.valueOf(3470164819L), "6hDv6V"),
					new ParameterValues(BigInteger.valueOf(3470165409L), "6hDvh6"),
					new ParameterValues(BigInteger.valueOf(3470167523L), "6hDvUx"),
					new ParameterValues(BigInteger.valueOf(3470169503L), "6hDwuF"),
					new ParameterValues(BigInteger.valueOf(3470171649L), "6hDx8F"),
					new ParameterValues(BigInteger.valueOf(3470172823L), "6hDxtV"),
					new ParameterValues(BigInteger.valueOf(3470178571L), "6hDzc2"),
					new ParameterValues(BigInteger.valueOf(3470180739L), "6hDzQp"),
					new ParameterValues(BigInteger.valueOf(3470182727L), "6hDAqF"),
					new ParameterValues(BigInteger.valueOf(3470202285L), "6hDGeT"),
					new ParameterValues(BigInteger.valueOf(3470207413L), "6hDHLi"),
					new ParameterValues(BigInteger.valueOf(3470212967L), "6hDKq4"),
					new ParameterValues(BigInteger.valueOf(3470213769L), "6hDKDT"),
					new ParameterValues(BigInteger.valueOf(3470233089L), "6hDRoZ"),
					new ParameterValues(BigInteger.valueOf(3470237383L), "6hDSF2"),
					new ParameterValues(BigInteger.valueOf(3470252497L), "6hDXaB"),
					new ParameterValues(BigInteger.valueOf(3470252609L), "6hDXcx"),
					new ParameterValues(BigInteger.valueOf(3470259877L), "6hDZmR"),
					new ParameterValues(BigInteger.valueOf(3470261427L), "6hDZPz"),
					new ParameterValues(BigInteger.valueOf(3470266691L), "6hE2ok"),
					new ParameterValues(BigInteger.valueOf(3470279363L), "6hE69P"),
					new ParameterValues(BigInteger.valueOf(3470283935L), "6hE7vD"),
					new ParameterValues(BigInteger.valueOf(3470285343L), "6hE7VV"),
					new ParameterValues(BigInteger.valueOf(3470287787L), "6hE8E4"),
					new ParameterValues(BigInteger.valueOf(3470302743L), "6hEd6V"),
					new ParameterValues(BigInteger.valueOf(3470302893L), "6hEd9v"),
					new ParameterValues(BigInteger.valueOf(3470308575L), "6hEeQt"),
					new ParameterValues(BigInteger.valueOf(3470320607L), "6hEipV"),
					new ParameterValues(BigInteger.valueOf(3470330361L), "6hEmj6"),
					new ParameterValues(BigInteger.valueOf(3470335967L), "6hEnYK"),
					new ParameterValues(BigInteger.valueOf(3470345891L), "6hEqVR"),
					new ParameterValues(BigInteger.valueOf(3470350937L), "6hEsqR"),
					new ParameterValues(BigInteger.valueOf(3470352537L), "6hEsUr"),
					new ParameterValues(BigInteger.valueOf(3470358539L), "6hEuFV"),
					new ParameterValues(BigInteger.valueOf(3470373757L), "6hEzdi"),
					new ParameterValues(BigInteger.valueOf(3470375341L), "6hEzFB"),
					new ParameterValues(BigInteger.valueOf(3470380131L), "6hEB7c"),
					new ParameterValues(BigInteger.valueOf(3470389271L), "6hEDPM"),
					new ParameterValues(BigInteger.valueOf(3470403775L), "6hEJ8R"),
					new ParameterValues(BigInteger.valueOf(3470404727L), "6hEJqg"),
					new ParameterValues(BigInteger.valueOf(3470415589L), "6hEMDx"),
					new ParameterValues(BigInteger.valueOf(3470417529L), "6hENdZ"),
					new ParameterValues(BigInteger.valueOf(3470420615L), "6hEP9c"),
					new ParameterValues(BigInteger.valueOf(3470421297L), "6hEPkX"),
					new ParameterValues(BigInteger.valueOf(3470423317L), "6hEPWM"),
					new ParameterValues(BigInteger.valueOf(3470428313L), "6hERqV"),
					new ParameterValues(BigInteger.valueOf(3470432637L), "6hESHt"),
					new ParameterValues(BigInteger.valueOf(3470436291L), "6hETNt"),
					new ParameterValues(BigInteger.valueOf(3470436759L), "6hETWx"),
					new ParameterValues(BigInteger.valueOf(3470464349L), "6hF39e"),
					new ParameterValues(BigInteger.valueOf(3470470361L), "6hF4VT"),
					new ParameterValues(BigInteger.valueOf(3470472183L), "6hF5ti"),
					new ParameterValues(BigInteger.valueOf(3470477937L), "6hF7bv"),
					new ParameterValues(BigInteger.valueOf(3470489505L), "6hFaBX"),
					new ParameterValues(BigInteger.valueOf(3470489971L), "6hFaKZ"),
					new ParameterValues(BigInteger.valueOf(3470496279L), "6hFcCK"),
					new ParameterValues(BigInteger.valueOf(3470507135L), "6hFfRV"),
					new ParameterValues(BigInteger.valueOf(3470523659L), "6hFkLP"),
					new ParameterValues(BigInteger.valueOf(3470533690L), "6hFoKL"),
					new ParameterValues(BigInteger.valueOf(3470533813L), "6hFoMT"),
					new ParameterValues(BigInteger.valueOf(3470535723L), "6hFpmP"),
					new ParameterValues(BigInteger.valueOf(3470538949L), "6hFqjr"),
					new ParameterValues(BigInteger.valueOf(3470542358L), "6hFrkd"),
					new ParameterValues(BigInteger.valueOf(3470544465L), "6hFrXx"),
					new ParameterValues(BigInteger.valueOf(3470545169L), "6hFsaF"),
					new ParameterValues(BigInteger.valueOf(3470549444L), "6hFtro"),
					new ParameterValues(BigInteger.valueOf(3470557964L), "6hFvYh"),
					new ParameterValues(BigInteger.valueOf(3470566524L), "6hFyvS"),
					new ParameterValues(BigInteger.valueOf(3470566707L), "6hFyz2"),
					new ParameterValues(BigInteger.valueOf(3470568182L), "6hFz1s"),
					new ParameterValues(BigInteger.valueOf(3470568690L), "6hFzad"),
					new ParameterValues(BigInteger.valueOf(3470568896L), "6hFzdL"),
					new ParameterValues(BigInteger.valueOf(3470582339L), "6hFDdx"),
					new ParameterValues(BigInteger.valueOf(3470583995L), "6hFDH6"),
					new ParameterValues(BigInteger.valueOf(3470584940L), "6hFDZo"),
					new ParameterValues(BigInteger.valueOf(3470585349L), "6hFE7r"),
					new ParameterValues(BigInteger.valueOf(3470588052L), "6hFEV3"),
					new ParameterValues(BigInteger.valueOf(3470592013L), "6hFG6k"),
					new ParameterValues(BigInteger.valueOf(3470596206L), "6hFHkC"),
					new ParameterValues(BigInteger.valueOf(3470597870L), "6hFHQj"),
					new ParameterValues(BigInteger.valueOf(3470601352L), "6hFJSm"),
					new ParameterValues(BigInteger.valueOf(3470601441L), "6hFJTT"),
					new ParameterValues(BigInteger.valueOf(3470612720L), "6hFNfm"),
					new ParameterValues(BigInteger.valueOf(3470619588L), "6hFQhL"),
					new ParameterValues(BigInteger.valueOf(3470621467L), "6hFQRa"),
					new ParameterValues(BigInteger.valueOf(3470621855L), "6hFQXR"),
					new ParameterValues(BigInteger.valueOf(3470623988L), "6hFRAC"),
					new ParameterValues(BigInteger.valueOf(3470625681L), "6hFS6P"),
					new ParameterValues(BigInteger.valueOf(3470627699L), "6hFSGB"),
					new ParameterValues(BigInteger.valueOf(3470635346L), "6hFUYs"),
					new ParameterValues(BigInteger.valueOf(3470636466L), "6hFViL"),
					new ParameterValues(BigInteger.valueOf(3470638261L), "6hFVQH"),
					new ParameterValues(BigInteger.valueOf(3470638313L), "6hFVRB"),
					new ParameterValues(BigInteger.valueOf(3470638629L), "6hFVX4"),
					new ParameterValues(BigInteger.valueOf(3470639603L), "6hFWeR"),
					new ParameterValues(BigInteger.valueOf(3470639683L), "6hFWge"),
					new ParameterValues(BigInteger.valueOf(3470641072L), "6hFWFb"),
					new ParameterValues(BigInteger.valueOf(3470643374L), "6hFXmS"),
					new ParameterValues(BigInteger.valueOf(3470652242L), "6hFZZL"),
					new ParameterValues(BigInteger.valueOf(3470654389L), "6hG1CM"),
					new ParameterValues(BigInteger.valueOf(3470659871L), "6hG3gi"),
					new ParameterValues(BigInteger.valueOf(3470660987L), "6hG3Ax"),
					new ParameterValues(BigInteger.valueOf(3470663195L), "6hG4fB"),
					new ParameterValues(BigInteger.valueOf(3470668558L), "6hG5R5"),
					new ParameterValues(BigInteger.valueOf(3470671481L), "6hG6Ht"),
					new ParameterValues(BigInteger.valueOf(3470674308L), "6hG7yd"),
					new ParameterValues(BigInteger.valueOf(3470676761L), "6hG8hv"),
					new ParameterValues(BigInteger.valueOf(3470676845L), "6hG8iX"),
					new ParameterValues(BigInteger.valueOf(3470679073L), "6hG8Yn"),
					new ParameterValues(BigInteger.valueOf(3470679342L), "6hG941"),
					new ParameterValues(BigInteger.valueOf(3470680720L), "6hG9sL"),
					new ParameterValues(BigInteger.valueOf(3470681716L), "6hG9KW"),
					new ParameterValues(BigInteger.valueOf(3470687574L), "6hGbuW"),
					new ParameterValues(BigInteger.valueOf(3470688024L), "6hGbCG"),
					new ParameterValues(BigInteger.valueOf(3470688570L), "6hGbN7"),
					new ParameterValues(BigInteger.valueOf(3470695182L), "6hGdL7"),
					new ParameterValues(BigInteger.valueOf(3470710691L), "6hGinv"),
					new ParameterValues(BigInteger.valueOf(3470714163L), "6hGjpn"),
					new ParameterValues(BigInteger.valueOf(3470714930L), "6hGjCA"),
					new ParameterValues(BigInteger.valueOf(3470717157L), "6hGkhZ"),
					new ParameterValues(BigInteger.valueOf(3470719158L), "6hGkTu"),
					new ParameterValues(BigInteger.valueOf(3470722065L), "6hGmKB"),
					new ParameterValues(BigInteger.valueOf(3470723055L), "6hGn3F"),
					new ParameterValues(BigInteger.valueOf(3470724663L), "6hGnwp"),
					new ParameterValues(BigInteger.valueOf(3470724941L), "6hGnBc"),
					new ParameterValues(BigInteger.valueOf(3470727743L), "6hGorv"),
					new ParameterValues(BigInteger.valueOf(3470729904L), "6hGp5L"),
					new ParameterValues(BigInteger.valueOf(3470730481L), "6hGpfH"),
					new ParameterValues(BigInteger.valueOf(3470749318L), "6hGuRu"),
					new ParameterValues(BigInteger.valueOf(3470751444L), "6hGvu9"),
					new ParameterValues(BigInteger.valueOf(3470768083L), "6hGAr2"),
					new ParameterValues(BigInteger.valueOf(3470775724L), "6hGCGL"),
					new ParameterValues(BigInteger.valueOf(3470780680L), "6hGEbd"),
					new ParameterValues(BigInteger.valueOf(3470782376L), "6hGEFs"),
					new ParameterValues(BigInteger.valueOf(3470790336L), "6hGH3G"),
					new ParameterValues(BigInteger.valueOf(3470793056L), "6hGHRA"),
					new ParameterValues(BigInteger.valueOf(3470806020L), "6hGMH7"),
					new ParameterValues(BigInteger.valueOf(3470807546L), "6hGNaq"),
					new ParameterValues(BigInteger.valueOf(3470811428L), "6hGPjm"),
					new ParameterValues(BigInteger.valueOf(3470816526L), "6hGQQf"),
					new ParameterValues(BigInteger.valueOf(3470818450L), "6hGRpq"),
					new ParameterValues(BigInteger.valueOf(3470819864L), "6hGRPN"),
					new ParameterValues(BigInteger.valueOf(3470820062L), "6hGRTd"),
					new ParameterValues(BigInteger.valueOf(3470822548L), "6hGSC5"),
					new ParameterValues(BigInteger.valueOf(3470823932L), "6hGT2W"),
					new ParameterValues(BigInteger.valueOf(3470830439L), "6hGUY8"),
					new ParameterValues(BigInteger.valueOf(3470832288L), "6hGVw1"),
					new ParameterValues(BigInteger.valueOf(3470833498L), "6hGVSS"),
					new ParameterValues(BigInteger.valueOf(3470836256L), "6hGWGq"),
					new ParameterValues(BigInteger.valueOf(3470836354L), "6hGWJ7"),
					new ParameterValues(BigInteger.valueOf(3470837753L), "6hGX9e"),
					new ParameterValues(BigInteger.valueOf(3470844930L), "6hGZgY"),
					new ParameterValues(BigInteger.valueOf(3470848414L), "6hH1j3"),
					new ParameterValues(BigInteger.valueOf(3470857619L), "6hH43K"),
					new ParameterValues(BigInteger.valueOf(3470858973L), "6hH4s6"),
					new ParameterValues(BigInteger.valueOf(3470863718L), "6hH5RU"),
					new ParameterValues(BigInteger.valueOf(3470864484L), "6hH667"),
					new ParameterValues(BigInteger.valueOf(3470873455L), "6hH8KM"),
					new ParameterValues(BigInteger.valueOf(3470883136L), "6hHbCG"),
					new ParameterValues(BigInteger.valueOf(3470883641L), "6hHbMp"),
					new ParameterValues(BigInteger.valueOf(3470886482L), "6hHcCo"),
					new ParameterValues(BigInteger.valueOf(3470886886L), "6hHcKm"),
					new ParameterValues(BigInteger.valueOf(3470888035L), "6hHd6a"),
					new ParameterValues(BigInteger.valueOf(3470889921L), "6hHdDF"),
					new ParameterValues(BigInteger.valueOf(3470894225L), "6hHeVT"),
					new ParameterValues(BigInteger.valueOf(3470900072L), "6hHgEG"),
					new ParameterValues(BigInteger.valueOf(3470901452L), "6hHh5u"),
					new ParameterValues(BigInteger.valueOf(3470903580L), "6hHhHb"),
					new ParameterValues(BigInteger.valueOf(3470904928L), "6hHi7q"),
					new ParameterValues(BigInteger.valueOf(3470905278L), "6hHids"),
					new ParameterValues(BigInteger.valueOf(3470907341L), "6hHiQ2"),
					new ParameterValues(BigInteger.valueOf(3470911419L), "6hHk3k"),
					new ParameterValues(BigInteger.valueOf(3470913019L), "6hHkvV"),
					new ParameterValues(BigInteger.valueOf(3470914553L), "6hHkYn"),
					new ParameterValues(BigInteger.valueOf(3470918204L), "6hHn4j"),
					new ParameterValues(BigInteger.valueOf(3470922194L), "6hHof7"),
					new ParameterValues(BigInteger.valueOf(3470922780L), "6hHoqd"),
					new ParameterValues(BigInteger.valueOf(3470925612L), "6hHpg3"),
					new ParameterValues(BigInteger.valueOf(3470929416L), "6hHqoC"),
					new ParameterValues(BigInteger.valueOf(3470931776L), "6hHr6j"),
					new ParameterValues(BigInteger.valueOf(3470953336L), "6hHxv3"),
					new ParameterValues(BigInteger.valueOf(3470956440L), "6hHyqy"),
					new ParameterValues(BigInteger.valueOf(3470961488L), "6hHzVA"),
					new ParameterValues(BigInteger.valueOf(3470961641L), "6hHzYe"),
					new ParameterValues(BigInteger.valueOf(3470970681L), "6hHCE6"),
					new ParameterValues(BigInteger.valueOf(3470971044L), "6hHCLm"),
					new ParameterValues(BigInteger.valueOf(3470971274L), "6hHCQj"),
					new ParameterValues(BigInteger.valueOf(3470973492L), "6hHDuy"),
					new ParameterValues(BigInteger.valueOf(3470973768L), "6hHDzj"),
					new ParameterValues(BigInteger.valueOf(3470976734L), "6hHEss"),
					new ParameterValues(BigInteger.valueOf(3470981830L), "6hHFYj"),
					new ParameterValues(BigInteger.valueOf(3470984013L), "6hHGBX"),
					new ParameterValues(BigInteger.valueOf(3470988633L), "6hHHZB"),
					new ParameterValues(BigInteger.valueOf(3470993664L), "6hHKum"),
					new ParameterValues(BigInteger.valueOf(3470995872L), "6hHL9q"),
					new ParameterValues(BigInteger.valueOf(3471001171L), "6hHMHM"),
					new ParameterValues(BigInteger.valueOf(3471008038L), "6hHPLb"),
					new ParameterValues(BigInteger.valueOf(3471017947L), "6hHSH2"),
					new ParameterValues(BigInteger.valueOf(3471020571L), "6hHTug"),
					new ParameterValues(BigInteger.valueOf(3471022985L), "6hHUcT"),
					new ParameterValues(BigInteger.valueOf(3471025642L), "6hHUZG"),
					new ParameterValues(BigInteger.valueOf(3471025846L), "6hHV4d"),
					new ParameterValues(BigInteger.valueOf(3471027420L), "6hHVwm"),
					new ParameterValues(BigInteger.valueOf(3471027922L), "6hHVF1"),
					new ParameterValues(BigInteger.valueOf(3471028298L), "6hHVMu"),
					new ParameterValues(BigInteger.valueOf(3471028968L), "6hHVZ3"),
					new ParameterValues(BigInteger.valueOf(3471033984L), "6hHXtw"),
					new ParameterValues(BigInteger.valueOf(3471037076L), "6hHYoQ"),
					new ParameterValues(BigInteger.valueOf(3471038510L), "6hHYPy"),
					new ParameterValues(BigInteger.valueOf(3471039240L), "6hHZ39"),
					new ParameterValues(BigInteger.valueOf(3471046359L), "6hJ29T"),
					new ParameterValues(BigInteger.valueOf(3471046452L), "6hJ2bu"),
					new ParameterValues(BigInteger.valueOf(3471055436L), "6hJ4Ro"),
					new ParameterValues(BigInteger.valueOf(3471057885L), "6hJ5zB"),
					new ParameterValues(BigInteger.valueOf(3471060580L), "6hJ6o5"),
					new ParameterValues(BigInteger.valueOf(3471062877L), "6hJ74F"),
					new ParameterValues(BigInteger.valueOf(3471063156L), "6hJ79u"),
					new ParameterValues(BigInteger.valueOf(3471068655L), "6hJ8Mi"),
					new ParameterValues(BigInteger.valueOf(3471069665L), "6hJ95H"),
					new ParameterValues(BigInteger.valueOf(3471076872L), "6hJbdY"),
					new ParameterValues(BigInteger.valueOf(3471083708L), "6hJdfQ"),
					new ParameterValues(BigInteger.valueOf(3471084708L), "6hJdy5"),
					new ParameterValues(BigInteger.valueOf(3471088381L), "6hJeDp"),
					new ParameterValues(BigInteger.valueOf(3471104998L), "6hJjzU"),
					new ParameterValues(BigInteger.valueOf(3471110522L), "6hJme9"),
					new ParameterValues(BigInteger.valueOf(3471123046L), "6hJpX5"),
					new ParameterValues(BigInteger.valueOf(3471131533L), "6hJstp"),
					new ParameterValues(BigInteger.valueOf(3471131548L), "6hJstE"),
					new ParameterValues(BigInteger.valueOf(3471131850L), "6hJsyS"),
					new ParameterValues(BigInteger.valueOf(3471136117L), "6hJtQr"),
					new ParameterValues(BigInteger.valueOf(3471139908L), "6hJuXN"),
					new ParameterValues(BigInteger.valueOf(3471142324L), "6hJvFs"),
					new ParameterValues(BigInteger.valueOf(3471145091L), "6hJwva"),
					new ParameterValues(BigInteger.valueOf(3471145750L), "6hJwGw"),
					new ParameterValues(BigInteger.valueOf(3471150749L), "6hJybH"),
					new ParameterValues(BigInteger.valueOf(3471159343L), "6hJAJT"),
					new ParameterValues(BigInteger.valueOf(3471161638L), "6hJBqs"),
					new ParameterValues(BigInteger.valueOf(3471168345L), "6hJDq6"),
					new ParameterValues(BigInteger.valueOf(3471171272L), "6hJEhy"),
					new ParameterValues(BigInteger.valueOf(3471176707L), "6hJFUg"),
					new ParameterValues(BigInteger.valueOf(3471193612L), "6hJLVJ"),
					new ParameterValues(BigInteger.valueOf(3471195219L), "6hJMpr"),
					new ParameterValues(BigInteger.valueOf(3471198710L), "6hJNrC"),
					new ParameterValues(BigInteger.valueOf(3471202816L), "6hJPEq"),
					new ParameterValues(BigInteger.valueOf(3471205734L), "6hJQwJ"),
					new ParameterValues(BigInteger.valueOf(3471206250L), "6hJQFC"),
					new ParameterValues(BigInteger.valueOf(3471213714L), "6hJSUj"),
					new ParameterValues(BigInteger.valueOf(3471218400L), "6hJUi7"),
					new ParameterValues(BigInteger.valueOf(3471220838L), "6hJV29"),
					new ParameterValues(BigInteger.valueOf(3471226305L), "6hJWDp"),
					new ParameterValues(BigInteger.valueOf(3471230395L), "6hJXRV"),
					new ParameterValues(BigInteger.valueOf(3471233782L), "6hJYSj"),
					new ParameterValues(BigInteger.valueOf(3471244465L), "6hK33v"),
					new ParameterValues(BigInteger.valueOf(3471244996L), "6hK3cE"),
					new ParameterValues(BigInteger.valueOf(3471249260L), "6hK4tb"),
					new ParameterValues(BigInteger.valueOf(3471257464L), "6hK6UC"),
					new ParameterValues(BigInteger.valueOf(3471265337L), "6hK9fn"),
					new ParameterValues(BigInteger.valueOf(3471276404L), "6hKcxb"),
					new ParameterValues(BigInteger.valueOf(3471278739L), "6hKder"),
					new ParameterValues(BigInteger.valueOf(3471283000L), "6hKeuU"),
					new ParameterValues(BigInteger.valueOf(3471283122L), "6hKex1"),
					new ParameterValues(BigInteger.valueOf(3471287918L), "6hKfXG"),
					new ParameterValues(BigInteger.valueOf(3471298806L), "6hKjcq"),
					new ParameterValues(BigInteger.valueOf(3471299338L), "6hKjmA"),
					new ParameterValues(BigInteger.valueOf(3471304242L), "6hKkP9"),
					new ParameterValues(BigInteger.valueOf(3471305444L), "6hKmaS"),
					new ParameterValues(BigInteger.valueOf(3471305986L), "6hKmkd"),
					new ParameterValues(BigInteger.valueOf(3471308338L), "6hKn2L"),
					new ParameterValues(BigInteger.valueOf(3471310391L), "6hKnDa"),
					new ParameterValues(BigInteger.valueOf(3471320476L), "6hKqD3"),
					new ParameterValues(BigInteger.valueOf(3471323630L), "6hKrzq"),
					new ParameterValues(BigInteger.valueOf(3471340916L), "6hKwHs"),
					new ParameterValues(BigInteger.valueOf(3471341778L), "6hKwYj"),
					new ParameterValues(BigInteger.valueOf(3471344136L), "6hKxEY"),
					new ParameterValues(BigInteger.valueOf(3471347507L), "6hKyF6"),
					new ParameterValues(BigInteger.valueOf(3471355775L), "6hKB8D"),
					new ParameterValues(BigInteger.valueOf(3471357731L), "6hKBHn"),
					new ParameterValues(BigInteger.valueOf(3471359692L), "6hKCib"),
					new ParameterValues(BigInteger.valueOf(3471365008L), "6hKDSQ"),
					new ParameterValues(BigInteger.valueOf(3471366538L), "6hKEkd"),
					new ParameterValues(BigInteger.valueOf(3471378900L), "6hKJ1m"),
					new ParameterValues(BigInteger.valueOf(3471380936L), "6hKJBs"),
					new ParameterValues(BigInteger.valueOf(3471382540L), "6hKK67"),
					new ParameterValues(BigInteger.valueOf(3471387416L), "6hKLxb"),
					new ParameterValues(BigInteger.valueOf(3471391110L), "6hKMCS"),
					new ParameterValues(BigInteger.valueOf(3471392198L), "6hKMXC"),
					new ParameterValues(BigInteger.valueOf(3471394398L), "6hKNBy"),
					new ParameterValues(BigInteger.valueOf(3471399184L), "6hKQ35"),
					new ParameterValues(BigInteger.valueOf(3471406804L), "6hKSis"),
					new ParameterValues(BigInteger.valueOf(3471407243L), "6hKSr2"),
					new ParameterValues(BigInteger.valueOf(3471408510L), "6hKSNS"),
					new ParameterValues(BigInteger.valueOf(3471410628L), "6hKTro"),
					new ParameterValues(BigInteger.valueOf(3471411192L), "6hKTB7"),
					new ParameterValues(BigInteger.valueOf(3471420220L), "6hKWhL"),
					new ParameterValues(BigInteger.valueOf(3471421129L), "6hKWyr"),
					new ParameterValues(BigInteger.valueOf(3471427928L), "6hKYzE"),
					new ParameterValues(BigInteger.valueOf(3471432170L), "6hKZQN"),
					new ParameterValues(BigInteger.valueOf(3471432842L), "6hL13o"),
					new ParameterValues(BigInteger.valueOf(3471439077L), "6hL2TT"),
					new ParameterValues(BigInteger.valueOf(3471444864L), "6hL4BE"),
					new ParameterValues(BigInteger.valueOf(3471473424L), "6hLd75"),
					new ParameterValues(BigInteger.valueOf(3471473988L), "6hLdgN"),
					new ParameterValues(BigInteger.valueOf(3471474232L), "6hLdm1"),
					new ParameterValues(BigInteger.valueOf(3471475720L), "6hLdME"),
					new ParameterValues(BigInteger.valueOf(3471484804L), "6hLguh"),
					new ParameterValues(BigInteger.valueOf(3471485480L), "6hLgFW"),
					new ParameterValues(BigInteger.valueOf(3471488378L), "6hLhxU"),
					new ParameterValues(BigInteger.valueOf(3471489939L), "6hLi1P"),
					new ParameterValues(BigInteger.valueOf(3471497748L), "6hLkks"),
					new ParameterValues(BigInteger.valueOf(3471499786L), "6hLkWA"),
					new ParameterValues(BigInteger.valueOf(3471508478L), "6hLows"),
					new ParameterValues(BigInteger.valueOf(3471509072L), "6hLoGG"),
					new ParameterValues(BigInteger.valueOf(3471509606L), "6hLoRU"),
					new ParameterValues(BigInteger.valueOf(3471511033L), "6hLphv"),
					new ParameterValues(BigInteger.valueOf(3471511386L), "6hLpoA"),
					new ParameterValues(BigInteger.valueOf(3471520902L), "6hLsdE"),
					new ParameterValues(BigInteger.valueOf(3471526541L), "6hLtTT"),
					new ParameterValues(BigInteger.valueOf(3471537361L), "6hLx7r"),
					new ParameterValues(BigInteger.valueOf(3471541024L), "6hLycA"),
					new ParameterValues(BigInteger.valueOf(3471545214L), "6hLzrQ"),
					new ParameterValues(BigInteger.valueOf(3471547914L), "6hLAfo"),
					new ParameterValues(BigInteger.valueOf(3471549134L), "6hLABq"),
					new ParameterValues(BigInteger.valueOf(3471553491L), "6hLBUx"),
					new ParameterValues(BigInteger.valueOf(3471557361L), "6hLD4g"),
					new ParameterValues(BigInteger.valueOf(3471581948L), "6hLLnb"),
					new ParameterValues(BigInteger.valueOf(3471583426L), "6hLLNE"),
					new ParameterValues(BigInteger.valueOf(3471605592L), "6hLToQ"),
					new ParameterValues(BigInteger.valueOf(3471606762L), "6hLTK1"),
					new ParameterValues(BigInteger.valueOf(3471626624L), "6hLZDs"),
					new ParameterValues(BigInteger.valueOf(3471649459L), "6hM7ra"),
					new ParameterValues(BigInteger.valueOf(3471650979L), "6hM7Tn"),
					new ParameterValues(BigInteger.valueOf(3471668536L), "6hMd75"),
					new ParameterValues(BigInteger.valueOf(3471668788L), "6hMdbq"),
					new ParameterValues(BigInteger.valueOf(3471716780L), "6hMsrS"),
					new ParameterValues(BigInteger.valueOf(3471728136L), "6hMvPE"),
					new ParameterValues(BigInteger.valueOf(3471734360L), "6hMxEY"),
					new ParameterValues(BigInteger.valueOf(3471735169L), "6hMxUV"),
					new ParameterValues(BigInteger.valueOf(3471743859L), "6hMAuK"),
					new ParameterValues(BigInteger.valueOf(3471746014L), "6hMB8U"),
					new ParameterValues(BigInteger.valueOf(3471761416L), "6hMFHs"),
					new ParameterValues(BigInteger.valueOf(3471784575L), "6hMNAK"),
					new ParameterValues(BigInteger.valueOf(3471792510L), "6hMQXy"),
					new ParameterValues(BigInteger.valueOf(3471798434L), "6hMSHG"),
					new ParameterValues(BigInteger.valueOf(3471807252L), "6hMVkJ"),
					new ParameterValues(BigInteger.valueOf(3471816360L), "6hMY3L"),
					new ParameterValues(BigInteger.valueOf(3471831918L), "6hN3F1"),
					new ParameterValues(BigInteger.valueOf(3471844090L), "6hN7hS"),
					new ParameterValues(BigInteger.valueOf(3471848112L), "6hN8ud"),
					new ParameterValues(BigInteger.valueOf(3471853844L), "6hNac3"),
					new ParameterValues(BigInteger.valueOf(3471860782L), "6hNcfE"),
					new ParameterValues(BigInteger.valueOf(3471866108L), "6hNdQu"),
					new ParameterValues(BigInteger.valueOf(3471866794L), "6hNe3j"),
					new ParameterValues(BigInteger.valueOf(3471895451L), "6hNnyp"),
					new ParameterValues(BigInteger.valueOf(3471900352L), "6hNp1U"),
					new ParameterValues(BigInteger.valueOf(3471902976L), "6hNpN9"),
					new ParameterValues(BigInteger.valueOf(3471931870L), "6hNyoj"),
					new ParameterValues(BigInteger.valueOf(3471936298L), "6hNzGE"),
					new ParameterValues(BigInteger.valueOf(3471939809L), "6hNAKc"),
					new ParameterValues(BigInteger.valueOf(3471948475L), "6hNDjB"),
					new ParameterValues(BigInteger.valueOf(3471956400L), "6hNFFf"),
					new ParameterValues(BigInteger.valueOf(3471967549L), "6hNJZt"),
					new ParameterValues(BigInteger.valueOf(3471973923L), "6hNLTn"),
					new ParameterValues(BigInteger.valueOf(3471981064L), "6hNP1u"),
					new ParameterValues(BigInteger.valueOf(3471984239L), "6hNPXe"),
					new ParameterValues(BigInteger.valueOf(3471987422L), "6hNQU7"),
					new ParameterValues(BigInteger.valueOf(3471991328L), "6hNS4s"),
					new ParameterValues(BigInteger.valueOf(3471996106L), "6hNTtQ"),
					new ParameterValues(BigInteger.valueOf(3472008951L), "6hNXii"),
					new ParameterValues(BigInteger.valueOf(3472024389L), "6hP2Tt"),
					new ParameterValues(BigInteger.valueOf(3472031076L), "6hP4SL"),
					new ParameterValues(BigInteger.valueOf(3472037848L), "6hP6Tw"),
					new ParameterValues(BigInteger.valueOf(3472048078L), "6hP9VU"),
					new ParameterValues(BigInteger.valueOf(3472050698L), "6hPaH5"),
					new ParameterValues(BigInteger.valueOf(3472055197L), "6hPc3D"),
					new ParameterValues(BigInteger.valueOf(3472064626L), "6hPeRd"),
					new ParameterValues(BigInteger.valueOf(3472065427L), "6hPf62"),
					new ParameterValues(BigInteger.valueOf(3472067396L), "6hPfEY"),
					new ParameterValues(BigInteger.valueOf(3472071508L), "6hPgTS"),
					new ParameterValues(BigInteger.valueOf(3472074356L), "6hPhJY"),
					new ParameterValues(BigInteger.valueOf(3472074472L), "6hPhLY"),
					new ParameterValues(BigInteger.valueOf(3472085672L), "6hPm75"),
					new ParameterValues(BigInteger.valueOf(3472096462L), "6hPpj7"),
					new ParameterValues(BigInteger.valueOf(3472142646L), "6hPD3o"),
					new ParameterValues(BigInteger.valueOf(3472167564L), "6hPLs1"),
					new ParameterValues(BigInteger.valueOf(3472174542L), "6hPNwj"),
					new ParameterValues(BigInteger.valueOf(3472182628L), "6hPQVJ"),
					new ParameterValues(BigInteger.valueOf(3472186974L), "6hPSdE"),
					new ParameterValues(BigInteger.valueOf(3472205606L), "6hPXKU"),
					new ParameterValues(BigInteger.valueOf(3472219148L), "6hQ2Mo"),
					new ParameterValues(BigInteger.valueOf(3472243594L), "6hQa3S"),
					new ParameterValues(BigInteger.valueOf(3472358868L), "6hQKjm"),
					new ParameterValues(BigInteger.valueOf(3472398736L), "6hQXaJ"),
					new ParameterValues(BigInteger.valueOf(3472431292L), "6hR7R3"),
					new ParameterValues(BigInteger.valueOf(3472439910L), "6hRapC"),
					new ParameterValues(BigInteger.valueOf(3472458394L), "6hRfUj"),
					new ParameterValues(BigInteger.valueOf(3472460514L), "6hRgwS"),
					new ParameterValues(BigInteger.valueOf(3472474666L), "6hRkJS"),
					new ParameterValues(BigInteger.valueOf(3472502220L), "6hRtVW"),
					new ParameterValues(BigInteger.valueOf(3472506540L), "6hRvdq"),
					new ParameterValues(BigInteger.valueOf(3472534740L), "6hRDAC"),
					new ParameterValues(BigInteger.valueOf(3472575120L), "6hRRAQ"),
					new ParameterValues(BigInteger.valueOf(3472575666L), "6hRRLf"),
					new ParameterValues(BigInteger.valueOf(3472602928L), "6hRZSh"),
					new ParameterValues(BigInteger.valueOf(3472631080L), "6hS9eE"),
					new ParameterValues(BigInteger.valueOf(3472660386L), "6hShWW"),
					new ParameterValues(BigInteger.valueOf(3472773572L), "6hSSAq"),
					new ParameterValues(BigInteger.valueOf(3472804260L), "6hT2Hw"),
					new ParameterValues(BigInteger.valueOf(3472819788L), "6hT7kf"),
					new ParameterValues(BigInteger.valueOf(3472820990L), "6hT7FY"),
					new ParameterValues(BigInteger.valueOf(3472830482L), "6hTavC"),
					new ParameterValues(BigInteger.valueOf(3472913142L), "6hTA5N"),
			});
		}

		@Test
		public void testDecoder() {
			assertThat(DECODER.decodeBigInteger(encodedValue), is(equalTo(decodedValue)));
		}

		@Test
		public void testEncoder() {
			assertThat(ENCODER.encodeToString(decodedValue), is(equalTo(encodedValue)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final BigInteger decodedValue;

			private final String encodedValue;


			public ParameterValues(BigInteger decodedValue, String encodedValue) {
				this.decodedValue = decodedValue;
				this.encodedValue = encodedValue;
			}


			public BigInteger getDecodedValue() {
				return decodedValue;
			}

			public String getEncodedValue() {
				return encodedValue;
			}

		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class RandomTest {

		private static final int REPEAT_COUNT = 100_000;

		private static final Random random = new Random();


		@Test
		public void test() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				byte[] bytes = RandomUtils.nextBytes(random, RandomUtils.nextInt(random, 0, 17));

				assertThat(DECODER.decode(ENCODER.encode(bytes)), is(equalTo(bytes)));
			});
		}

	}

}
