/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.nio.ByteBuffer;
import java.text.MessageFormat;

import org.eclipse.titan.runtime.core.Param_Types.Module_Parameter;
import org.eclipse.titan.runtime.core.Param_Types.Module_Parameter.basic_check_bits_t;
import org.eclipse.titan.runtime.core.RAW.RAW_Force_Omit;
import org.eclipse.titan.runtime.core.RAW.RAW_coding_par;
import org.eclipse.titan.runtime.core.RAW.RAW_enc_tr_pos;
import org.eclipse.titan.runtime.core.RAW.RAW_enc_tree;
import org.eclipse.titan.runtime.core.TTCN_EncDec.coding_type;
import org.eclipse.titan.runtime.core.TTCN_EncDec.error_type;
import org.eclipse.titan.runtime.core.TTCN_EncDec.raw_order_t;

/**
 * TTCN-3 float
 * @author Arpad Lovassy
 * @author Farkas Izabella Ingrid
 * @author Andrea Palfi
 */
public class TitanFloat extends Base_Type {
	/**
	 * float value.
	 */
	private Ttcn3Float float_value;

	//static members PLUS_INFINITY, MINUS_INFINITY, NOT_A_NUMBER
	public static final double PLUS_INFINITY = Double.POSITIVE_INFINITY;
	public static final double MINUS_INFINITY = Double.NEGATIVE_INFINITY;
	public static final double NOT_A_NUMBER = Double.NaN;
	public static final double MIN_DECIMAL_FLOAT = 1.0E-4;
	public static final double MAX_DECIMAL_FLOAT = 1.0E+10;

	/**
	 * Initializes to unbound value.
	 * */
	public TitanFloat() {
		super();
	}

	/**
	 * Initializes to a given value.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanFloat(final double otherValue) {
		float_value = new Ttcn3Float(otherValue);
	}

	/**
	 * Initializes to a given value.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanFloat(final Ttcn3Float otherValue) {
		float_value = otherValue;
	}

	/**
	 * Initializes to a given value.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanFloat(final TitanFloat otherValue) {
		otherValue.must_bound("Copying an unbound float value.");

		float_value = otherValue.float_value;
	}

	public Double getValue() {
		return float_value.getValue();
	}

	/**
	 * Assigns the other value to this value.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new value object.
	 */
	public TitanFloat operator_assign(final double otherValue) {
		float_value = new Ttcn3Float(otherValue);

		return this;
	}

	/**
	 * Assigns the other value to this value.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new value object.
	 */
	public TitanFloat operator_assign(final Ttcn3Float otherValue) {
		float_value = otherValue;

		return this;
	}

	/**
	 * Assigns the other value to this value.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new value object.
	 */
	public TitanFloat operator_assign(final TitanFloat otherValue) {
		otherValue.must_bound("Assignment of an unbound float value.");

		if (otherValue != this) {
			float_value = otherValue.float_value;
		}

		return this;
	}

	@Override
	public TitanFloat operator_assign(final Base_Type otherValue) {
		if (otherValue instanceof TitanFloat) {
			return operator_assign((TitanFloat) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to float", otherValue));
	}

	@Override
	public boolean is_bound() {
		return float_value != null;
	}

	@Override
	public boolean is_present() {
		return is_bound();
	};

	@Override
	public boolean is_value() {
		return float_value != null;
	}

	// isspecial
	public static TitanBoolean isSpecial(final double aOtherValue) {
		return new TitanBoolean(aOtherValue == PLUS_INFINITY || aOtherValue == MINUS_INFINITY || Double.isNaN(aOtherValue));
	}

	// check_numeric
	public static void checkNumeric(final double floatValue, final String errorMsg) {
		if (isSpecial(floatValue).getValue()) {
			throw new TtcnError(MessageFormat.format("{0} must be a numeric value instead of {1}", errorMsg, floatValue));
		}
	}

	/**
	 * this + aOtherValue
	 * originally operator+
	 */

	public TitanFloat add() {
		must_bound("Unbound float operand of unary + operator.");

		return new TitanFloat(float_value);
	}

	/**
	 * this + aOtherValue
	 * originally operator+
	 */
	public TitanFloat add(final double aOtherValue) {
		must_bound("Unbound left operand of float addition.");

		return new TitanFloat(float_value.add(aOtherValue));
	}

	/**
	 * this + aOtherValue
	 * originally operator+
	 */
	public TitanFloat add(final Ttcn3Float aOtherValue) {
		must_bound("Unbound left operand of float addition.");

		return new TitanFloat(float_value.add(aOtherValue.getValue()));
	}

	/**
	 * this + aOtherValue
	 * originally operator+
	 */
	public TitanFloat add(final TitanFloat aOtherValue) {
		must_bound("Unbound left operand of float addition.");
		aOtherValue.must_bound("Unbound right operand of float addition.");

		return new TitanFloat(float_value.add(aOtherValue.float_value.getValue()));
	}

	/**
	 * this - aOtherValue
	 * originally operator-
	 */
	public TitanFloat sub() {
		must_bound("Unbound float operand of unary - operator (negation).");

		return new TitanFloat(-float_value.getValue());
	}

	/**
	 * this - aOtherValue
	 * originally operator-
	 */
	public TitanFloat sub(final double aOtherValue) {
		must_bound("Unbound left operand of float subtraction.");

		return new TitanFloat(float_value.sub(aOtherValue));
	}

	/**
	 * this - aOtherValue
	 * originally operator-
	 */
	public TitanFloat sub(final Ttcn3Float aOtherValue) {
		must_bound("Unbound left operand of float subtraction.");

		return new TitanFloat(float_value.sub(aOtherValue.getValue()));
	}

	/**
	 * this - aOtherValue
	 * originally operator-
	 */
	public TitanFloat sub(final TitanFloat aOtherValue) {
		must_bound("Unbound left operand of float subtraction.");
		aOtherValue.must_bound("Unbound right operand of float subtraction.");

		return new TitanFloat(float_value.sub(aOtherValue.float_value.getValue()));
	}

	/**
	 * this * aOtherValue
	 * originally operator*
	 */
	public TitanFloat mul(final double aOtherValue) {
		must_bound("Unbound left operand of float multiplication.");

		return new TitanFloat(float_value.mul(aOtherValue));
	}

	/**
	 * this * aOtherValue
	 * originally operator*
	 */
	public TitanFloat mul(final Ttcn3Float aOtherValue) {
		must_bound("Unbound left operand of float multiplication.");

		return new TitanFloat(float_value.mul(aOtherValue.getValue()));
	}

	/**
	 * this * aOtherValue
	 * originally operator*
	 */
	public TitanFloat mul(final TitanFloat aOtherValue) {
		must_bound("Unbound left operand of float multiplication.");
		aOtherValue.must_bound("Unbound right operand of float multiplication.");

		return new TitanFloat(float_value.mul(aOtherValue.float_value.getValue()));
	}

	/**
	 * this / aOtherValue
	 * originally operator/
	 */
	public TitanFloat div(final double aOtherValue) {
		must_bound("Unbound left operand of float division.");

		if (aOtherValue == 0.0) {
			throw new TtcnError("Float division by zero.");
		}

		return new TitanFloat(float_value.div(aOtherValue));
	}

	/**
	 * this / aOtherValue
	 * originally operator/
	 */
	public TitanFloat div(final Ttcn3Float aOtherValue) {
		must_bound("Unbound left operand of float division.");

		if (aOtherValue.getValue() == 0.0) {
			throw new TtcnError("Float division by zero.");
		}

		return new TitanFloat(float_value.div(aOtherValue.getValue()));
	}

	/**
	 * this / aOtherValue
	 * originally operator/
	 */
	public TitanFloat div(final TitanFloat aOtherValue) {
		must_bound("Unbound left operand of float division.");
		aOtherValue.must_bound("Unbound right operand of float division.");

		final double otherValue = aOtherValue.float_value.getValue();
		if (otherValue == 0.0) {
			throw new TtcnError("Float division by zero.");
		}

		return new TitanFloat(float_value.div(otherValue));
	}

	/**
	 * Checks if the current value is equivalent to the provided one.
	 *
	 * operator== in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are equivalent.
	 */
	public boolean operator_equals(final double otherValue) {
		must_bound("Unbound left operand of float comparison.");

		return float_value.operator_equals(otherValue);
	}

	/**
	 * Checks if the current value is equivalent to the provided one.
	 *
	 * operator== in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are equivalent.
	 */
	public boolean operator_equals(final Ttcn3Float otherValue) {
		must_bound("Unbound left operand of float comparison.");

		return float_value.operator_equals(otherValue.getValue());
	}

	/**
	 * Checks if the current value is equivalent to the provided one.
	 *
	 * operator== in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are equivalent.
	 */
	public boolean operator_equals(final TitanFloat otherValue) {
		must_bound("Unbound left operand of float comparison.");
		otherValue.must_bound("Unbound right operand of float comparison.");

		return float_value.operator_equals(otherValue.float_value.getValue());
	}

	@Override
	public boolean operator_equals(final Base_Type otherValue) {
		if (otherValue instanceof TitanFloat) {
			return operator_equals((TitanFloat) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to charstring", otherValue));
	}

	/**
	 * Checks if the current value is not equivalent to the provided one.
	 *
	 * operator!= in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are not equivalent.
	 */
	public boolean operator_not_equals(final double otherValue) {
		return !operator_equals(otherValue);
	}

	/**
	 * Checks if the current value is not equivalent to the provided one.
	 *
	 * operator!= in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are not equivalent.
	 */
	public boolean operator_not_equals(final Ttcn3Float otherValue) {
		return !operator_equals(otherValue);
	}

	/**
	 * Checks if the current value is not equivalent to the provided one.
	 *
	 * operator!= in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are not equivalent.
	 */
	public boolean operator_not_equals(final TitanFloat otherValue) {
		return !operator_equals(otherValue);
	}

	// originally operator <
	public boolean isLessThan(final double otherValue) {
		must_bound("Unbound left operand of float comparison.");

		return float_value.isLessThan(otherValue);
	}

	// originally operator <
	public boolean isLessThan(final Ttcn3Float otherValue) {
		must_bound("Unbound left operand of float comparison.");

		return float_value.isLessThan(otherValue.getValue());
	}

	// originally operator <
	public boolean isLessThan(final TitanFloat otherValue) {
		must_bound("Unbound left operand of float comparison.");
		otherValue.must_bound("Unbound right operand of float comparison.");

		return float_value.isLessThan(otherValue.float_value.getValue());
	}

	// originally operator >
	public boolean isGreaterThan(final double otherValue) {
		must_bound("Unbound left operand of float comparison.");

		return float_value.isGreaterThan(otherValue);
	}

	// originally operator >
	public boolean isGreaterThan(final Ttcn3Float otherValue) {
		must_bound("Unbound left operand of float comparison.");

		return float_value.isGreaterThan(otherValue.getValue());
	}

	// originally operator >
	public boolean isGreaterThan(final TitanFloat otherValue) {
		must_bound("Unbound left operand of float comparison.");
		otherValue.must_bound("Unbound right operand of float comparison.");

		return float_value.isGreaterThan(otherValue.float_value.getValue());
	}

	// originally operator <=
	public boolean isLessThanOrEqual(final double otherValue) {
		return !isGreaterThan(otherValue);
	}

	// originally operator <=
	public boolean isLessThanOrEqual(final Ttcn3Float otherValue) {
		return !isGreaterThan(otherValue);
	}

	// originally operator <=
	public boolean isLessThanOrEqual(final TitanFloat otherValue) {
		return !isGreaterThan(otherValue);
	}

	// originally operator >=
	public boolean isGreaterThanOrEqual(final double otherValue) {
		return !isLessThan(otherValue);
	}

	// originally operator >=
	public boolean isGreaterThanOrEqual(final Ttcn3Float otherValue) {
		return !isLessThan(otherValue);
	}

	// originally operator >=
	public boolean isGreaterThanOrEqual(final TitanFloat otherValue) {
		return !isLessThan(otherValue);
	}

	@Override
	public void log() {
		if (float_value != null) {
			log_float(float_value.getValue());
		} else {
			TTCN_Logger.log_event_unbound();
		}
	}

	@Override
	public void clean_up() {
		float_value = null;
	}

	static void log_float(final double float_val) {
		if ((float_val > -TitanFloat.MAX_DECIMAL_FLOAT && float_val <= -TitanFloat.MIN_DECIMAL_FLOAT)
				|| (float_val >= MIN_DECIMAL_FLOAT && float_val < TitanFloat.MAX_DECIMAL_FLOAT) || (float_val == 0.0)) {
			TTCN_Logger.log_event("%f", float_val);
		} else if (float_val == PLUS_INFINITY) {
			TTCN_Logger.log_event_str("infinity");
		} else if (float_val == MINUS_INFINITY) {
			TTCN_Logger.log_event_str("-infinity");
		} else if (float_val != float_val) {
			TTCN_Logger.log_event_str("not_a_number");
		} else {
			TTCN_Logger.log_event("%e", float_val);
		}
	}

	/** 
	 * Do not use this function!<br>
	 * It is provided by Java and currently used for debugging.
	 * But it is not part of the intentionally provided interface,
	 *   and so can be changed without notice. 
	 * <p>
	 * JAVA DESCRIPTION:
	 * <p>
	 * {@inheritDoc}
	 *  */
	@Override
	public String toString() {
		if (float_value == null) {
			return "<unbound>";
		}

		return float_value.toString();
	}

	@Override
	/** {@inheritDoc} */
	public void encode_text(final Text_Buf text_buf) {
		must_bound("Text encoder: Encoding an unbound float value.");

		text_buf.push_double(float_value.getValue());
	}

	@Override
	/** {@inheritDoc} */
	public void decode_text(final Text_Buf text_buf) {
		operator_assign(text_buf.pull_double());
	}

	// static add
	public static TitanFloat add(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float addition.");

		return new TitanFloat(otherValue.add(doubleValue));
	}

	// static sub
	public static TitanFloat sub(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float subtraction.");

		return new TitanFloat(doubleValue - otherValue.getValue());
	}

	// static mul
	public static TitanFloat mul(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float multiplication.");

		return new TitanFloat(otherValue.mul(doubleValue));
	}

	// static div
	public static TitanFloat div(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float division.");

		final double value = otherValue.float_value.getValue();
		if (value == 0.0) {
			throw new TtcnError("Float division by zero.");
		}

		return new TitanFloat(doubleValue / otherValue.getValue());
	}

	// static operator_equals
	public static boolean operator_equals(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float comparison.");

		return otherValue.operator_equals(doubleValue);
	}

	// static operator_not_equals
	public static boolean operator_not_equals(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float comparison.");

		return otherValue.operator_not_equals(doubleValue);
	}

	// static isLess
	public static TitanBoolean isLessThan(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float comparison.");

		return new TitanBoolean(otherValue.isGreaterThan(new TitanFloat(doubleValue)));
	}

	// static isGreaterThan
	public static TitanBoolean isGreaterThan(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float comparison.");

		return new TitanBoolean(otherValue.isLessThan(new TitanFloat(doubleValue)));
	}

	// static isLessThanOrEqual
	public static TitanBoolean isLessThanOrEqual(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float comparison.");

		return new TitanBoolean(otherValue.isGreaterThanOrEqual(new TitanFloat(doubleValue)));
	}

	// static isGreaterThanOrEqual
	public static TitanBoolean isGreaterThanOrEqual(final double doubleValue, final TitanFloat otherValue) {
		otherValue.must_bound("Unbound right operand of float comparison.");

		return new TitanBoolean(otherValue.isLessThanOrEqual(new TitanFloat(doubleValue)));
	}

	@Override
	/** {@inheritDoc} */
	public void encode(final TTCN_Typedescriptor p_td, final TTCN_Buffer p_buf, final coding_type p_coding, final int flavour) {
		switch (p_coding) {
		case CT_RAW: {
			final TTCN_EncDec_ErrorContext errorContext = new TTCN_EncDec_ErrorContext("While RAW-encoding type '%s': ", p_td.name);
			if (p_td.raw == null) {
				TTCN_EncDec_ErrorContext.error_internal("No RAW descriptor available for type '%s'.", p_td.name);
			}

			final RAW_enc_tr_pos rp = new RAW_enc_tr_pos(0, null);
			final RAW_enc_tree root = new RAW_enc_tree(true, null, rp, 1, p_td.raw);
			RAW_encode(p_td, root);
			root.put_to_buf(p_buf);

			errorContext.leaveContext();
			break;
		}
		default:
			throw new TtcnError(MessageFormat.format("Unknown coding method requested to encode type `{0}''", p_td.name));
		}
	}

	@Override
	/** {@inheritDoc} */
	public void decode(final TTCN_Typedescriptor p_td, final TTCN_Buffer p_buf, final coding_type p_coding, final int flavour) {
		switch (p_coding) {
		case CT_RAW: {
			final TTCN_EncDec_ErrorContext errorContext = new TTCN_EncDec_ErrorContext("While RAW-decoding type '%s': ", p_td.name);
			if (p_td.raw == null) {
				TTCN_EncDec_ErrorContext.error_internal("No RAW descriptor available for type '%s'.", p_td.name);
			}
			raw_order_t order;
			switch (p_td.raw.top_bit_order) {
			case TOP_BIT_LEFT:
				order = raw_order_t.ORDER_LSB;
				break;
			case TOP_BIT_RIGHT:
			default:
				order = raw_order_t.ORDER_MSB;
				break;
			}

			if (RAW_decode(p_td, p_buf, p_buf.get_len() * 8, order) < 0) {
				TTCN_EncDec_ErrorContext.error(error_type.ET_INCOMPL_ANY, "Can not decode type '%s', because invalid or incomplete message was received", p_td.name);
			}

			errorContext.leaveContext();
			break;
		}
		default:
			throw new TtcnError(MessageFormat.format("Unknown coding method requested to decode type `{0}''", p_td.name));
		}
	}

	@Override
	/** {@inheritDoc} */
	public int RAW_encode(final TTCN_Typedescriptor p_td, final RAW_enc_tree myleaf) {
		char[] bc;
		char[] dv;
		final int length = p_td.raw.fieldlength / 8;
		double tmp = float_value.getValue();
		if (!is_bound()) {
			TTCN_EncDec_ErrorContext.error(error_type.ET_UNBOUND, "Encoding an unbound value.");
			tmp = 0.0;
		}
		if (Double.isNaN(tmp)) {
			TTCN_EncDec_ErrorContext.error_internal("Value is NaN.");
		}
		if (length > RAW.RAW_INT_ENC_LENGTH) {
			myleaf.data_array = bc = new char[length];
		} else {
			bc = myleaf.data_array;
		}
		if (length == 8) {
			final byte[] tmp_dv = new byte[8];
			ByteBuffer.wrap(tmp_dv).putDouble(tmp);
			dv = new char[8];
			for (int i = 0; i < tmp_dv.length; i++) {
				dv[i] = (char) tmp_dv[i];
			}
			for (int i = 0, k = 7; i < 8; i++, k--) {
				bc[i] = dv[k];
			}
		} else if (length == 4) {
			if (tmp == 0.0) {
				for (int i = 0; i < 4; i++) {
					bc[i] = 0;
				}
			} else if (tmp == -0.0) {
				for (int i = 0; i < 4; i++) {
					bc[i] = 0;
				}
				bc[0] |= 0x80;
			} else {
				int index = 0;
				final int adj = 1;

				final byte[] tmp_dv = new byte[8];
				ByteBuffer.wrap(tmp_dv).putDouble(tmp);
				dv = new char[8];
				for (int i = 0; i < tmp_dv.length; i++) {
					dv[i] = (char) tmp_dv[i];
				}
				bc[0] = (char) (tmp_dv[index] & 0x80);
				int exponent = tmp_dv[index] & 0x7F;
				exponent <<= 4;
				index += adj;
				exponent += (tmp_dv[index] & 0xF0) >> 4;
				exponent -= 1023;

				if (exponent > 127) {
					TTCN_EncDec_ErrorContext.error(error_type.ET_LEN_ERR,"The float value %f is out of the range of the single precision: %s", float_value.getValue(), p_td.name);
					tmp = 0.0;
					exponent = 0;
				} else if (exponent < -127) {
					TTCN_EncDec_ErrorContext.error(error_type.ET_FLOAT_TR, "The float value %f is too small to represent it in single precision: %s", float_value.getValue(), p_td.name);
					tmp = 0.0;
					exponent = 0;
				} else {
					exponent += 127;
				}
				bc[0] |= (exponent >> 1) & 0x7F;
				bc[1] = (char) (((exponent << 7) & 0x80) | ((dv[index] & 0x0F) << 3) | ((dv[index + adj] & 0xE0) >> 5));
				index += adj;
				bc[2] = (char) (((dv[index] & 0x1F) << 3) | ((dv[index + adj] & 0xE0) >> 5));
				index += adj;
				bc[3] = (char) (((dv[index] & 0x1F) << 3) | ((dv[index + adj] & 0xE0) >> 5));
			}
		} else {
			TTCN_EncDec_ErrorContext.error_internal("Invalid FLOAT length {0}", length);
		}
		return myleaf.length = p_td.raw.fieldlength;
	}

	@Override
	/** {@inheritDoc} */
	public int RAW_decode(final TTCN_Typedescriptor p_td, final TTCN_Buffer buff, int limit, final raw_order_t top_bit_ord, final boolean no_err, final int sel_field, final boolean first_call, final RAW_Force_Omit force_omit) {
		final int prepaddlength = buff.increase_pos_padd(p_td.raw.prepadding);
		limit -= prepaddlength;
		int decode_length = p_td.raw.fieldlength;
		final TTCN_EncDec_ErrorContext errorcontext = new TTCN_EncDec_ErrorContext();
		if (p_td.raw.fieldlength > limit || p_td.raw.fieldlength > buff.unread_len_bit()) {
			if (no_err) {
				errorcontext.leaveContext();
				return -1;
			}
			TTCN_EncDec_ErrorContext.error(error_type.ET_LEN_ERR, "There is not enough bits in the buffer to decode type %s.", p_td.name);
			decode_length = limit > (int) buff.unread_len_bit() ? buff.unread_len_bit() : limit;
			float_value = new Ttcn3Float(0.0);
			decode_length += buff.increase_pos_padd(p_td.raw.padding);
			errorcontext.leaveContext();
			return decode_length + prepaddlength;
		}

		double tmp = 0.0;
		final char[] data = new char[16];
		final RAW_coding_par cp = new RAW_coding_par();
		boolean orders = false;
		if (p_td.raw.bitorderinoctet == raw_order_t.ORDER_MSB) {
			orders = true;
		}
		if (p_td.raw.bitorderinfield == raw_order_t.ORDER_MSB) {
			orders = !orders;
		}
		cp.bitorder = orders ? raw_order_t.ORDER_MSB : raw_order_t.ORDER_LSB;
		orders = false;
		if (p_td.raw.byteorder == raw_order_t.ORDER_MSB) {
			orders = true;
		}
		if (p_td.raw.bitorderinfield == raw_order_t.ORDER_MSB) {
			orders = !orders;
		}
		cp.byteorder = orders ? raw_order_t.ORDER_MSB : raw_order_t.ORDER_LSB;
		cp.fieldorder = p_td.raw.fieldorder;
		cp.hexorder = raw_order_t.ORDER_LSB;
		buff.get_b(decode_length, data, cp, top_bit_ord);
		if (decode_length == 64) {
			final byte[] tmp_dv = new byte[8];
			char[] dv = new char[8];
			for (int i = 0, k = 7; i < 8; i++, k--) {
				dv[i] = data[k];
			}
			for (int i = 0; i < tmp_dv.length; i++) {
				tmp_dv[i] = (byte) dv[i];
			}
			tmp = ByteBuffer.wrap(tmp_dv).getDouble();
			if (Double.isNaN(tmp)) {
				if (no_err) {
					errorcontext.leaveContext();
					return -1;
				}
				TTCN_EncDec_ErrorContext.error(error_type.ET_LEN_ERR, "Not a Number received for type %s.", p_td.name);
				tmp = 0.0;
			}
		} else if (decode_length == 32) {
			final int sign = (data[0] & 0x80) >> 7;
			int exponent = ((data[0] & 0x7F) << 1) | ((data[1] & 0x80) >> 7);
			int fraction = ((data[1] & 0x7F) << 1) | ((data[2] & 0x80) >> 7);
			fraction <<= 8;
			fraction += ((data[2] & 0x7F) << 1) | ((data[3] & 0x80) >> 7);
			fraction <<= 7;
			fraction += data[3] & 0x7F;
			if (exponent == 0 && fraction == 0) {
				tmp = sign != 0 ? -0.0 : 0.0;
			} else if (exponent == 0xFF && fraction != 0) {
				if (no_err) {
					errorcontext.leaveContext();
					return -1;
				}
				TTCN_EncDec_ErrorContext.error(error_type.ET_LEN_ERR, "Not a Number received for type %s.", p_td.name);
				tmp = 0.0;
			}  else if (exponent == 0 && fraction != 0) {
				final double sign_v = sign != 0 ? -1.0 : 1.0;
				tmp = sign_v * ((double)(fraction) / 8388608.0) * Math.pow(2.0, -126.0);
			} else {
				final double sign_v = sign != 0 ? -1.0 : 1.0;
				exponent -= 127;
				tmp = sign_v * (1.0 + (double)(fraction) / 8388608.0) * Math.pow(2.0,(double)(exponent));
			}
		}
		decode_length += buff.increase_pos_padd(p_td.raw.padding);
		float_value = new Ttcn3Float(tmp);
		errorcontext.leaveContext();
		return decode_length + prepaddlength;
	}

	@Override
	/** {@inheritDoc} */
	public void set_param(final Module_Parameter param) {
		param.basic_check(basic_check_bits_t.BC_VALUE.getValue(), "float value");
		switch (param.get_type()) {
		case MP_Float:
			operator_assign(param.get_float());
			break;
		case MP_Expression:
			switch (param.get_expr_type()) {
			case EXPR_NEGATE: {
				final TitanFloat operand = new TitanFloat();
				operand.set_param(param.get_operand1());
				operator_assign(operand.sub());
				break; }
			case EXPR_ADD: {
				final TitanFloat operand1 = new TitanFloat();
				final TitanFloat operand2 = new TitanFloat();
				operand1.set_param(param.get_operand1());
				operand2.set_param(param.get_operand2());
				operator_assign(operand1.add(operand2));
				break;
			}
			case EXPR_SUBTRACT: {
				final TitanFloat operand1 = new TitanFloat();
				final TitanFloat operand2 = new TitanFloat();
				operand1.set_param(param.get_operand1());
				operand2.set_param(param.get_operand2());
				operator_assign(operand1.sub(operand2));
				break;
			}
			case EXPR_MULTIPLY: {
				final TitanFloat operand1 = new TitanFloat();
				final TitanFloat operand2 = new TitanFloat();
				operand1.set_param(param.get_operand1());
				operand2.set_param(param.get_operand2());
				operator_assign(operand1.mul(operand2));
				break;
			}
			case EXPR_DIVIDE: {
				final TitanFloat operand1 = new TitanFloat();
				final TitanFloat operand2 = new TitanFloat();
				operand1.set_param(param.get_operand1());
				operand2.set_param(param.get_operand2());
				if (operand2.operator_equals(0)) {
					param.error("Floating point division by zero.");
				}
				operator_assign(operand1.div(operand2));
				break; }
			default:
				param.expr_type_error("a float");
				break;
			}
			break;
		default:
			param.type_error("float value");
			break;
		}
	}

}
