/**
 * Copyright (C) 2010 Julien Buret <julien.buret@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package testclasses.conditional;

public class ConditionalBrancheCompareVar {

	// int
	public void compareEQ(int var1, int var2) {
		if (var1 == var2) {
			return;
		}
	}

	public void compareNEQ(int var1, int var2) {
		if (var1 != var2) {
			return;
		}
	}

	public void compareINF(int var1, int var2) {
		if (var1 < var2) {
			return;
		}
	}

	public void compareSUP(int var1, int var2) {
		if (var1 > var2) {
			return;
		}
	}

	public void compareINFEQ(int var1, int var2) {
		if (var1 <= var2) {
			return;
		}
	}

	public void compareSUPEQ(int var1, int var2) {
		if (var1 >= var2) {
			return;
		}
	}

	// float
	public void compareEQ(float var1, float var2) {
		if (var1 == var2) {
			return;
		}
	}

	public void compareNEQ(float var1, float var2) {
		if (var1 != var2) {
			return;
		}
	}

	public void compareINF(float var1, float var2) {
		if (var1 < var2) {
			return;
		}
	}

	public void compareSUP(float var1, float var2) {
		if (var1 > var2) {
			return;
		}
	}

	public void var2(float var1, float var2) {
		if (var1 <= var2) {
			return;
		}
	}

	public void compareSUPEQ(float var1, float var2) {
		if (var1 >= var2) {
			return;
		}
	}

	// long
	public void compareEQ(long var1, long var2) {
		if (var1 == var2) {
			return;
		}
	}

	public void compareNEQ(long var1, long var2) {
		if (var1 != var2) {
			return;
		}
	}

	public void compareINF(long var1, long var2) {
		if (var1 < var2) {
			return;
		}
	}

	public void compareSUP(long var1, long var2) {
		if (var1 > var2) {
			return;
		}
	}

	public void compareINFEQ(long var1, long var2) {
		if (var1 <= var2) {
			return;
		}
	}

	public void compareSUPEQ(long var1, long var2) {
		if (var1 >= var2) {
			return;
		}
	}

}
