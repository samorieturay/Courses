/*
 * Door.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import java.awt.Color;

/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class Door extends MapSite
{
	private final Room[] rooms = new Room[2];
	private boolean open;

	public Door(final Room r1, final Room r2)
	{
		open = false;
		rooms[0] = r1;
		rooms[1] = r2;
	}

	public final boolean isOpen()
	{
		return open;
	}

	public final void setOpen(boolean open)
	{
		this.open = open;
	}

	public final Room getOtherSide(final Room r)
	{
		if (rooms[0] == r)
			return rooms[1];
		else if (rooms[1] == r)
			return rooms[0];
		else
			return null;
	}

	@Override
	public void enter()
	{
		if (!open) {
			System.out.println("Opened the door.");
			open = true;
		}

		super.notifyEntryListeners();
	}

	@Override
	public Color getColor()
	{
		return Color.LIGHT_GRAY;
	}
}
