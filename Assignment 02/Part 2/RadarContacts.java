// Name			: Joe Turner
// Username		: jeturner	
// Desciption	: 
public class RadarContacts {
	public static void main(String[] args)
	{
		// vars
		double radius = Double.parseDouble(args[0]);
		double mode = Double.parseDouble(args[1]);
		String [][] radar = null;
		int friendly = 0;
		int nonFriendly = 0;
		int numRecords = StdIn.readInt();
		for (int i = 0; i < numRecords - 1; i++ )
		{
			for (int j = 0; j < 3; j++)
			{
				radar[i][j] = StdIn.readString();
			}
		}
		for (int i = 0; i < numRecords - 1; i++ )
		{
			if (radar[i][2].equals("?"))
			{
				nonFriendly += 1;
			}
			else
			{
				friendly += 1;
			}
		}
		if (mode == 0)
		{
			System.out.println("Non-friendly aircraft: " + nonFriendly);
		}
		if (mode == 1)
		{
			System.out.println("Friendly aircraft: " + friendly);
		}
		if (mode == 2)
		{
			System.out.println("Friendly aircraft: " + friendly);
			System.out.println("Non-friendly aircraft: " + nonFriendly);

			for (int i = 0; i < numRecords - 1; i++ )
			{
				double pointP1 = Double.parseDouble(radar[i][0]);
				double pointP2 = Double.parseDouble(radar[i][1]);
				String callSign1 = (radar[i][2]);

				for (int j = 0; j < numRecords - 1; j++)
				{
					if (j != i)
					{
						double pointQ1 = Double.parseDouble(radar[j][0]);
						double pointQ2 = Double.parseDouble(radar[j][1]);
						String callSign2 = (radar[j][2]);
						double distance = Math.sqrt( ( (pointP1 - pointQ1) * (pointP1 - pointQ1) ) + ( (pointP2- pointQ2) * (pointP2- pointQ2) ) );
						if (distance <= radius)
						{
							System.out.println();
							System.out.println(callSign1 + ":");
							System.out.println(callSign2 + " (" + distance + ")");
						}
					}
				}
			}
		}
	}
}
