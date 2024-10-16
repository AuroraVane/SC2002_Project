import pandas as pd

# Load the Excel file
df = pd.read_excel('Staff_List.xlsx')

df.to_csv('Staff_List.txt', sep='|', index=False)